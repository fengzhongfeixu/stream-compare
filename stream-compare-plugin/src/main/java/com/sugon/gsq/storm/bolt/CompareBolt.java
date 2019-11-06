package com.sugon.gsq.storm.bolt;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.apache.log4j.Logger;
import org.apache.storm.Constants;
import org.apache.storm.shade.com.google.common.collect.Lists;
import org.apache.storm.shade.com.google.common.collect.Maps;
import org.apache.storm.task.OutputCollector;
import org.apache.storm.task.TopologyContext;
import org.apache.storm.topology.OutputFieldsDeclarer;
import org.apache.storm.topology.base.BaseRichBolt;
import org.apache.storm.tuple.Fields;
import org.apache.storm.tuple.Tuple;
import org.apache.storm.tuple.Values;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class CompareBolt extends BaseRichBolt {

    private static final long serialVersionUID = 2275469466058227358L;
    private Logger logger;
    private OutputCollector collector;
    private Connection connection;
    private String tableName;
    //private String resultTableName;
    private String[] compareFieldOfJdbc;

    private String[] compareField;

    private String sql;

    private List<String> list = new ArrayList<>();

    private Map<String, Object> environment = Maps.newHashMap();

    @Override
    public void prepare(Map map, TopologyContext topologyContext, OutputCollector outputCollector) {
        logger = Logger.getLogger(CompareBolt.class);
        this.collector = outputCollector;

        try {
            environment = (Map) map.get("topology.environment");
            Class.forName(environment.get("driver").toString());
            connection = DriverManager.getConnection(environment.get("url").toString()
                    , environment.get("username").toString()
                    , environment.get("pwd").toString());

            tableName = environment.get("compareTableName").toString();
            //resultTableName = environment.get("resultTableName").toString();
            compareFieldOfJdbc = environment.get("compareFieldOfJdbc").toString().split(",");
            compareField = environment.get("compareField").toString().split(",");
            //拼接对比sql
            sql = createQuerySql(compareFieldOfJdbc);
        } catch (Exception e) {
            logger.error(e.getMessage());
        }
    }

    @Override
    public void execute(Tuple tuple) {
        this.collector.ack(tuple);
        if (tuple.getSourceComponent().equals(Constants.SYSTEM_COMPONENT_ID)) {
            try {
                System.out.println(list.size() + "=========================================================");
            } catch (Exception e) {
                logger.error(e.getMessage());
            }
        } else {
            try {
                JSONObject kafkaJson = JSON.parseObject(tuple.getString(0));
                statData(kafkaJson);
            } catch (Exception e) {
                logger.error("Json转换失败" + e.getMessage());
            }
        }
    }

    /**
     * 启动比对
     *
     * @param kafkaJson
     * @throws Exception
     */
    private void statData(JSONObject kafkaJson) throws Exception {
        try {
            Statement statement = connection.createStatement();
            ResultSet rs;
            if (compareFieldOfJdbc.length > 1) {
                ArrayList<String> values = Lists.newArrayList();
                values.add(tableName);

                for (int i = 0; i < compareFieldOfJdbc.length; i++) {
                    values.add(compareFieldOfJdbc[i]);
                    values.add(kafkaJson.getString(compareField[i]));
                }
                rs = statement.executeQuery(
                        String.format(sql, values.toArray())
                );
            } else {
                rs = statement.executeQuery(
                        String.format(sql, tableName, compareFieldOfJdbc[0], kafkaJson.getString(compareField[0]))
                );
            }
            ResultSetMetaData metaData = rs.getMetaData();
            int columnCount = metaData.getColumnCount();

            JSONObject toKafka = new JSONObject();
            JSONArray blackDb = new JSONArray();

            boolean isBlack = false;
            while (rs.next()) {
                JSONObject jsonObj = new JSONObject();
                for (int i = 1; i <= columnCount; i++) {
                    String columnName = metaData.getColumnLabel(i);
                    String value = rs.getString(columnName);
                    jsonObj.put(columnName, value);
                }
                blackDb.add(jsonObj);
                isBlack = true;
            }
            statement.close();

            if (isBlack) {
                toKafka.put("blackdb", blackDb);
                toKafka.put("kafka", kafkaJson);
                toKafka.put("type", environment.get("compareType").toString());
                // 保存对比结果
                //saveBlackListResult(toKafka);

                collector.emit(new Values(toKafka.toJSONString()));
            }

        } catch (Exception e) {
            logger.error("对比失败" + e);
        }
    }

//    /**
//     * 保存比对结果
//     *
//     * @param toKafka
//     * @throws Exception
//     */
//    private void saveBlackListResult(JSONObject toKafka) throws Exception {
//        Statement statement = connection.createStatement();
//        statement.execute(
//                String.format("insert into %s (id, data) values ('%s', '%s')",
//                        resultTableName, UUID.randomUUID().toString().replaceAll("-", ""), toKafka.toJSONString()));
//        statement.close();
//    }

    @Override
    public void declareOutputFields(OutputFieldsDeclarer outputFieldsDeclarer) {
        outputFieldsDeclarer.declare(new Fields("message"));
    }

    /**
     * 工具方法：拼接sql语句
     *
     * @param compareFieldOfJdbc
     * @return String
     */
    public String createQuerySql(String[] compareFieldOfJdbc) {
        StringBuffer part = new StringBuffer("select * from %s where");
        if (compareFieldOfJdbc.length > 1) {
            for (int i = 0; i < compareFieldOfJdbc.length; i++) {
                if (i == compareFieldOfJdbc.length - 1) {
                    part.append(" %s = '%s' and lgzt <> '2'");
                } else {
                    part.append(" %s = '%s' and");
                }
            }
        } else {
            part.append(" %s = '%s' and lgzt <> '2'");
        }
        return part.toString();
    }

}
