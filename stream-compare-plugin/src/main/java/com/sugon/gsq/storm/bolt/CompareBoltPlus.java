package com.sugon.gsq.storm.bolt;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.sugon.gsq.storm.entity.CompareSourceEntity;
import com.sugon.gsq.storm.entity.ExecutionModuleEntity;
import org.apache.log4j.Logger;
import org.apache.storm.Constants;
import org.apache.storm.shade.com.google.common.collect.Lists;
import org.apache.storm.task.OutputCollector;
import org.apache.storm.task.TopologyContext;
import org.apache.storm.topology.OutputFieldsDeclarer;
import org.apache.storm.topology.base.BaseRichBolt;
import org.apache.storm.tuple.Fields;
import org.apache.storm.tuple.Tuple;
import org.apache.storm.tuple.Values;

import java.sql.*;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class CompareBoltPlus extends BaseRichBolt {

    private static final long serialVersionUID = 8883428503124569763L;
    private Logger logger;
    private OutputCollector collector;

    //需要进行多次对比的集合
    private List<CompareSourceEntity> compareSourceEntities = new LinkedList<>();

    //执行模块
    private List<ExecutionModuleEntity> executionModuleEntities = new LinkedList<>();

    //回推字段
    private String[] fields;

    private Map<String, Object> environment;

    @Override
    public void prepare(Map map, TopologyContext topologyContext, OutputCollector outputCollector) {
        logger = Logger.getLogger(CompareBoltPlus.class);
        this.collector = outputCollector;
        try {
            environment = (Map) map.get("topology.environment");
            Object object = environment.get("compareCores");
            fields = environment.get("keys").toString().split(",");
            compareSourceEntities = JSONObject.parseArray(object.toString(), CompareSourceEntity.class);
            for(CompareSourceEntity compareSourceEntity : compareSourceEntities){
                Class.forName(compareSourceEntity.getDriver());
                Connection connection = DriverManager.getConnection(compareSourceEntity.getUrl()
                        , compareSourceEntity.getUsername()
                        , compareSourceEntity.getPwd());
                ExecutionModuleEntity entity = new ExecutionModuleEntity();
                entity.setConnection(connection);
                entity.setPassword(compareSourceEntity.getPwd());
                entity.setCompareTableName(compareSourceEntity.getCompareTableName());
                entity.setCompareField(compareSourceEntity.getCompareField().split(","));
                entity.setCompareFieldOfJdbc(compareSourceEntity.getCompareFieldOfJdbc().split(","));
                entity.setSql(createQuerySql(entity.getCompareFieldOfJdbc(),compareSourceEntity.getCompareTableName()));

                executionModuleEntities.add(entity);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void execute(Tuple tuple) {
        this.collector.ack(tuple);
        if (tuple.getSourceComponent().equals(Constants.SYSTEM_COMPONENT_ID)) {
            try {
                logger.error("What is this ghost !!!!!!!!!!!!!!!!!!!!");
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            try {
                JSONObject kafkaJson = JSON.parseObject(tuple.getString(0));
                //从kafka中挑选回推信息
                JSONObject kafkaJson_ = makeKafkaMessage(kafkaJson);
                //初始化回推kafka信息
                JSONObject toKafka = new JSONObject();
                toKafka.put("kafka", kafkaJson_);
                toKafka.put("result", new JSONArray());
                toKafka.put("blackdb", new JSONArray());
                //基本常量
                JSONObject setting = new JSONObject();
                setting.put("timestamp", System.currentTimeMillis());

                if(environment.get("layout")!=null){
                    toKafka.put("layout", environment.get("layout").toString());
                }
                toKafka.put("setting", setting);
                toKafka.put("type", environment.get("compareType").toString());
                toKafka.put("username",environment.get("belongUser").toString());
                toKafka.put("model",environment.get("model").toString());
                //布控需要
                toKafka.put("identification",kafkaJson_.get(fields[0]));
                toKafka.put("classification","ry_jbxx.wbswry");
                //循环比对
                for(ExecutionModuleEntity eme : executionModuleEntities){
                    // 是否数据库连接需要重连
                    reConnection(eme);
                    // 开始进行数据处理
                    statData(kafkaJson,toKafka,eme);
                }
                // 比对没有结果不回推kafka
                JSONArray result = (JSONArray) toKafka.get("result");
                boolean isBack = false;
                for(int i=0 ; i<result.size() ; i++){
                    if(Boolean.parseBoolean(result.getJSONObject(i).getString("hit"))){
                        isBack = true;
                        break;
                    }
                }
                if(isBack)
                    collector.emit(new Values(toKafka.toJSONString()));
            } catch (Exception e) {
                logger.error("JSON file assembly failure : " + e.getMessage());
            }
        }
    }

    /**
     * 启动比对
     *
     * @param kafkaJson
     * @throws Exception
     */
    private void statData(JSONObject kafkaJson, JSONObject toKafka, ExecutionModuleEntity executionUnit) throws Exception {
        try {
            Statement statement = executionUnit.getConnection().createStatement();
            //获取结果集
            ResultSet rs = forValues(statement,kafkaJson,executionUnit);
            ResultSetMetaData metaData = rs.getMetaData();
            int columnCount = metaData.getColumnCount();

            //比对结果
            JSONObject compare = new JSONObject();
            compare.put("tableName",executionUnit.getCompareTableName());
            compare.put("data",new JSONArray());

            boolean isBlack = false;
            while (rs.next()) {
                JSONObject jsonObj = new JSONObject();
                for (int i = 1; i <= columnCount; i++) {
                    String columnName = metaData.getColumnLabel(i);
                    String value = rs.getString(columnName);
                    jsonObj.put(columnName, value);
                }
                ((JSONArray)compare.get("data")).add(jsonObj);
                isBlack = true;
            }
            //填入对比结果
            ((JSONArray)toKafka.get("blackdb")).add(compare);
            statement.close();

            if (isBlack) {
                //对比成功的记录
                JSONObject object = new JSONObject();
                object.put("tableName",executionUnit.getCompareTableName());
                object.put("hit","true");

                ((JSONArray)toKafka.get("result")).add(object);
            }else{
                //黑名单中没有
                JSONObject object = new JSONObject();
                object.put("tableName",executionUnit.getCompareTableName());
                object.put("hit","false");

                ((JSONArray)toKafka.get("result")).add(object);
            }
        } catch (Exception e) {
            logger.error("Compare is failed : " + e.getMessage());
        }
    }

    /**
     * 生成对比结果集
     * @param statement
     * @param kafkaJson
     * @param eme
     * @return
     * @throws SQLException
     */
    private ResultSet forValues(Statement statement, JSONObject kafkaJson, ExecutionModuleEntity eme) throws SQLException {
        ResultSet rs;
        if (eme.getCompareFieldOfJdbc().length > 1) {
            ArrayList<String> values = Lists.newArrayList();

            for (String compareField : eme.getCompareField()) {
                values.add(kafkaJson.getString(compareField));
            }
            rs = statement.executeQuery(
                    String.format(eme.getSql(), values.toArray())
            );
        } else {
            rs = statement.executeQuery(
                    String.format(eme.getSql(), kafkaJson.getString(eme.getCompareField()[0]))
            );
        }
        return rs;
    }

    /**
     * 工具方法：拼接sql语句
     *
     * @param compareFieldOfJdbc
     * @return String
     */
    public String createQuerySql(String[] compareFieldOfJdbc,String compareTableName) {
        StringBuffer part = new StringBuffer("select * from " + compareTableName + " where ");
        if (compareFieldOfJdbc.length > 1) {
            for (int i = 0; i < compareFieldOfJdbc.length; i++) {
                if (i == compareFieldOfJdbc.length - 1) {
                    part.append(compareFieldOfJdbc[i] + " = '%s'");
                } else {
                    part.append(compareFieldOfJdbc[i] + " = '%s' and ");
                }
            }
        } else {
            part.append(compareFieldOfJdbc[0] + " = '%s'");
        }
        return part.toString();
    }

    /**
     * 挑选回推kafka的信息
     * @param kafkaJson
     */
    private JSONObject makeKafkaMessage(JSONObject kafkaJson){
        JSONObject result = new JSONObject();
        for(String field : fields){
            result.put(field,kafkaJson.get(field));
        }
        return result;
    }

    /**
     * 挑选回推kafka的信息
     * @param eme
     */
    private void reConnection(ExecutionModuleEntity eme) throws SQLException {
        if(eme.getConnection().isValid(0)){
            return;
        } else {
            DatabaseMetaData metaData = eme.getConnection().getMetaData();
            Connection connection = DriverManager.getConnection(metaData.getURL()
                    , metaData.getUserName().split("@")[0]
                    , eme.getPassword());
            eme.setConnection(connection);
        }
    }

    @Override
    public void declareOutputFields(OutputFieldsDeclarer outputFieldsDeclarer) {
        outputFieldsDeclarer.declare(new Fields("message"));
    }

}
