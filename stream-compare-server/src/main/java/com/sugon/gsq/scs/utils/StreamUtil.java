package com.sugon.gsq.scs.utils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.sugon.gsq.scs.entity.stream.StreamBlacklistSourceEntity;
import com.sugon.gsq.scs.entity.stream.StreamStormTaskEntity;
import kafka.api.OffsetRequest;
import org.apache.storm.Config;
import org.apache.storm.generated.ClusterSummary;
import org.apache.storm.generated.KillOptions;
import org.apache.storm.generated.TopologySummary;
import org.apache.storm.kafka.*;
import org.apache.storm.kafka.bolt.KafkaBolt;
import org.apache.storm.kafka.bolt.mapper.FieldNameBasedTupleToKafkaMapper;
import org.apache.storm.kafka.bolt.selector.DefaultTopicSelector;
import org.apache.storm.spout.SchemeAsMultiScheme;
import org.apache.storm.thrift.TException;
import org.apache.storm.utils.NimbusClient;
import org.apache.storm.utils.Utils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.*;
import java.util.*;

/*
 * ClassName: StreamUtil
 * Author: Administrator
 * Date: 2019/5/27 9:00
 */
public class StreamUtil {

    private static final Logger logger = LoggerFactory.getLogger(StreamUtil.class);

    /**
     * 判断任务是否运行
     * @param jobName
     * @return boolean
     */
    public static boolean isRunning(String jobName, List<String> nodes) throws TException {
        Map stormConf = Utils.readStormConfig();
        List<String> ips = new LinkedList<>();
        Set<String> ports = new HashSet<>();
        nodes.forEach(x -> {
            String[] ipAndPort = x.split(":");
            ips.add(ipAndPort[0]);
            ports.add(ipAndPort[1]);
        });
        if(ports.size() != 1){
            logger.warn("Some machines have different port !!!");
        }
        stormConf.put(Config.NIMBUS_SEEDS, ips);
        // ports中应该只有一个元素
        stormConf.put(Config.NIMBUS_THRIFT_PORT, Integer.parseInt(ports.iterator().next()));
        NimbusClient nimbus = NimbusClient.getConfiguredClient(stormConf);
        ClusterSummary clusterSummary = nimbus.getClient().getClusterInfo();
        for (TopologySummary currentTopology : clusterSummary.get_topologies()) {
            if(currentTopology.get_name().equals(jobName)){
                return currentTopology.get_status().toLowerCase().equals("active");
            }
        }
        return false;
    }

    /**
     * 根据任务名称杀死任务
     * @param jobName
     * @return boolean
     */
    public static boolean killTaskByName(String jobName, List<String> nodes) throws TException {
        Map stormConf = Utils.readStormConfig();
        List<String> ips = new LinkedList<>();
        Set<String> ports = new HashSet<>();
        nodes.forEach(x -> {
            String[] ipAndPort = x.split(":");
            ips.add(ipAndPort[0]);
            ports.add(ipAndPort[1]);
        });
        if(ports.size() != 1){
            logger.warn("Some machines have different port !!!");
        }
        stormConf.put(Config.NIMBUS_SEEDS, ips);
        stormConf.put(Config.NIMBUS_THRIFT_PORT, Integer.parseInt(ports.iterator().next()));
        NimbusClient nimbus = NimbusClient.getConfiguredClient(stormConf);
        KillOptions options = new KillOptions();
        options.set_wait_secs(1);
        nimbus.getClient().killTopologyWithOpts(jobName, options);
        return true;
    }

    /**
     * 获取数据库表的元数据信息
     * @param sbse
     * @return JSONObject
     */
    public static JSONObject getMetadataOfTable(StreamBlacklistSourceEntity sbse) throws SQLException, ClassNotFoundException {
        JSONObject result = new JSONObject();
        Class.forName(sbse.getDriver());
        Connection connection = DriverManager.getConnection(sbse.getUrl()
                , sbse.getUsername()
                , sbse.getPwd());
        Statement statement = connection.createStatement();
        ResultSet rs = statement.executeQuery(String.format("select * from %s where 1=2",
                sbse.getCompareTableName()));
        ResultSetMetaData metaData = rs.getMetaData();
        LinkedList<Map<String,String>> list = Lists.newLinkedList();
        int columnCount = metaData.getColumnCount();
        for (int i = 1; i <= columnCount; i++) {
            String columnName = metaData.getColumnLabel(i);
            String columnType = metaData.getColumnTypeName(i);
            Map<String,String> row = Maps.newHashMap();
            row.put("name",columnName);
            row.put("type",columnType);
            list.add(row);
        }
        result.put("result",list.toArray());
        result.put("status",true);
        return result;
    }

    /**
     * 测试数据库连接是否成功
     * @param sbse
     * @return
     */
    public static JSONObject testJdbcConnection(StreamBlacklistSourceEntity sbse){
        JSONObject result = new JSONObject();
        try {
            Class.forName(sbse.getDriver());
            Connection connection = DriverManager.getConnection(sbse.getUrl()
                    , sbse.getUsername()
                    , sbse.getPwd());
            result.put("status",true);
            result.put("message","The connection is available");
        } catch (Exception e) {
            e.printStackTrace();
            result.put("status",false);
            result.put("message",e.getMessage());
        }
        return result;
    }

    /**
     * 判断表是否存在
     * @param sbse
     * @return
     */
    public static JSONObject tableExistOrNot(StreamBlacklistSourceEntity sbse){
        JSONObject result = new JSONObject();
        try {
            Class.forName(sbse.getDriver());
            Connection connection = DriverManager.getConnection(sbse.getUrl()
                    , sbse.getUsername()
                    , sbse.getPwd());
            ResultSet rs = connection.getMetaData().getTables(null,
                    null, sbse.getCompareTableName(), null);
            if (rs.next()) {
                result.put("status",true);
                result.put("message","exist");
            }else {
                result.put("status",true);
                result.put("message","inexistence");
            }
        } catch (Exception e) {
            e.printStackTrace();
            result.put("status",false);
            result.put("message",e.getMessage());
        }
        return result;
    }

    /**
     * 为前台展示storm任务名称去掉所属用户
     * @param jobName
     * @return
     */
    public static String prefixUser(String jobName){
        return jobName.split("_")[jobName.split("_").length - 1];
    }

    /**
     * 获取 storm 基础配置
     * @param nodes
     * @return
     */
    public static Map getStormClientConfig(List<String> nodes){
        Map stormConf = Utils.readStormConfig();
        // 获取配置文件中的storm参数
        List<String> ips = new LinkedList<>();
        Set<String> ports = new HashSet<>();
        nodes.forEach(x -> {
            String[] ipAndPort = x.split(":");
            ips.add(ipAndPort[0]);
            ports.add(ipAndPort[1]);
        });
        if(ports.size() != 1){
            logger.warn("Some machines have different port !!!");
        }
        // 将参数值配置好
        stormConf.put(Config.NIMBUS_SEEDS, ips);
        // ports中应该只有一个元素
        stormConf.put(Config.NIMBUS_THRIFT_PORT, Integer.parseInt(ports.iterator().next()));
        return stormConf;
    }

    /**
     * 获取 storm 基础配置
     * @param taskEntity
     * @return
     */
    public static Config getStreamTaskEnvironment(StreamStormTaskEntity taskEntity){
        Config conf = new Config();
        // task基础配置
        conf.setNumWorkers(taskEntity.getWorkerNum());
        conf.setDebug(false);
        // 配置storm从节点参数
        Map<String,Object> environment = new HashMap<>();
        environment.put("keys",taskEntity.getKeys());
        environment.put("layout",taskEntity.getLayout());
        environment.put("model",taskEntity.getModel());
        environment.put("belongUser",taskEntity.getBelongUser());
        environment.put("compareType",taskEntity.getCompareType());
        // 由于需要网络传输，所以参数瘦身
        String cores = JSON.toJSON(taskEntity.getStormCompareCores()).toString();
        JSONArray coresList = JSON.parseArray(cores);
        for(int i=0;i<coresList.size();i++){
            JSONObject core = coresList.getJSONObject(i);
            Iterator<String> iteratorKeys = core.keySet().iterator();
            while(iteratorKeys.hasNext()){
                String key = iteratorKeys.next();
                if(!Arrays.asList(Constants.COMPARE_CORE_PARAMS).contains(key))
                    iteratorKeys.remove();
            }
        }
        environment.put("compareCores",JSON.toJSON(coresList).toString());

        // 如果是自用且layout有值则回推kafka
        boolean model = taskEntity.getModel().equals("public");
        boolean isLayout = taskEntity.getLayout()==null||taskEntity.getLayout()==""||taskEntity.getLayout()=="{}";
        if(model || isLayout) environment.remove("layout");
        // 添加task环境变量
        conf.setEnvironment(environment);
        return conf;
    }

    /**
     * 创建kafka数据处理环节配置文件
     * @param taskEntity
     * @return
     */
    public static KafkaSpout createSpoutConfigOfKafka(StreamStormTaskEntity taskEntity){
        // 创建kafka数据源
        ZkHosts brokerHosts = new ZkHosts(taskEntity.getStormKafkaSourceEntity().getZkServer());
        String topic = taskEntity.getStormKafkaSourceEntity().getTopic();
        String zkRoot = taskEntity.getStormKafkaSourceEntity().getZkRoot();
        SpoutConfig spoutConf = new SpoutConfig(brokerHosts, topic, zkRoot, Constants.KAFKA_SPOUT_ID);
//        spoutConf.ignoreZkOffsets = false;  // 拓扑第一次读取从头开始，重启从zk读取
        spoutConf.maxOffsetBehind = 2000L;  // 超过2000条则跳过offset
//        spoutConf.startOffsetTime = 0L; // 从zk里读取offset
//        spoutConf.useStartOffsetTimeIfOffsetOutOfRange = false;  // 请求offset不存在，则从头读取
        spoutConf.startOffsetTime = OffsetRequest.LatestTime();   // 在最新的offset读取
        spoutConf.zkServers = ZookeeperUtil.getZkServer(brokerHosts);
        spoutConf.zkPort = ZookeeperUtil.getZookeeperPort(taskEntity.getStormKafkaSourceEntity().getZkServer());
        spoutConf.scheme = new SchemeAsMultiScheme(new StringScheme());

        return new KafkaSpout(spoutConf);
    }

    /**
     * 创建storm自带的kafka数据处理环节
     * @param taskEntity
     * @return
     */
    public static KafkaBolt createBoltOfKafka(StreamStormTaskEntity taskEntity){
        Properties props = new Properties();
        props.put("bootstrap.servers", taskEntity.getKafkaServer());
        props.put("acks", "1");
        props.put("key.serializer", Constants.KAFKA_KEY_SERIALIZER);
        props.put("value.serializer", Constants.KAFKA_VALUE_SERIALIZER);
        return new KafkaBolt()
                .withProducerProperties(props)
                .withTopicSelector(new DefaultTopicSelector(taskEntity.getResult()))
                .withTupleToKafkaMapper(new FieldNameBasedTupleToKafkaMapper());
    }

}
