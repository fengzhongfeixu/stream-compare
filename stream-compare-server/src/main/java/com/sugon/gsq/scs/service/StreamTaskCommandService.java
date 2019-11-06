package com.sugon.gsq.scs.service;

import com.alibaba.fastjson.JSONObject;
import com.sugon.gsq.scs.authorization.security.SecurityUtils;
import com.sugon.gsq.scs.config.StormConfig;
import com.sugon.gsq.scs.dao.StreamStormTaskDAO;
import com.sugon.gsq.scs.entity.stream.*;
import com.sugon.gsq.scs.utils.Constants;
import com.sugon.gsq.scs.utils.StreamUtil;
import org.apache.storm.StormSubmitter;
import org.apache.storm.kafka.KafkaSpout;
import org.apache.storm.kafka.bolt.KafkaBolt;
import org.apache.storm.shade.org.json.simple.JSONValue;
import org.apache.storm.thrift.TException;
import org.apache.storm.topology.TopologyBuilder;
import org.apache.storm.topology.base.BaseRichBolt;
import org.apache.storm.utils.NimbusClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.Map;

@Service
public class StreamTaskCommandService {

    @Autowired
    private StormConfig stormConfig;

    @Autowired
    private StreamStormTaskDAO stormTaskDAO;

    @Autowired
    private StreamTaskMetadataService metadataService;

    private static final Logger logger = LoggerFactory.getLogger(StreamTaskCommandService.class);

    public JSONObject getMetadataOfTable(StreamBlacklistSourceEntity sbse) throws SQLException, ClassNotFoundException {
        return StreamUtil.getMetadataOfTable(sbse);
    }

    public JSONObject testJdbcConnection(StreamBlacklistSourceEntity sbse){
        return StreamUtil.testJdbcConnection(sbse);
    }

    public JSONObject tableExistOrNot(StreamBlacklistSourceEntity sbse){
        return StreamUtil.tableExistOrNot(sbse);
    }

    public boolean killTaskById(String id) throws TException {
        StreamStormTaskEntity sste = stormTaskDAO.selectByPrimaryKey(id);
        String jobName = sste.getJobName();
        StreamUtil.killTaskByName(jobName, stormConfig.getNimbus().getNodes());
        return true;
    }

    public boolean submitCompareTask(String id) throws Exception {
        StreamStormTaskEntity taskEntity = metadataService.getStormCompareTaskById(id);
        // 添加所属用户到任务名称
        taskEntity.setJobName(SecurityUtils.getCurrentUserLogin() + "_" + taskEntity.getJobName());
        // 创建主配置文件
        Map stormConf = StreamUtil.getStormClientConfig(stormConfig.getNimbus().getNodes());
        // 合并environment和主配置文件
        stormConf.putAll(StreamUtil.getStreamTaskEnvironment(taskEntity));
        // 获取需要比对的kafka数据源
        KafkaSpout kafkaSpout = StreamUtil.createSpoutConfigOfKafka(taskEntity);
        // 获取回推结果的kafka数据源
        KafkaBolt resultBolt = StreamUtil.createBoltOfKafka(taskEntity);
        // 创建storm任务拓扑图
        TopologyBuilder builder = new TopologyBuilder();
        builder.setSpout(Constants.KAFKA_SPOUT_ID, kafkaSpout, Constants.STORM_PARALLEL_NUM)
                .setNumTasks(Constants.STORM_TASK_NUM);
        builder.setBolt(Constants.KAFKA_COMPARE_ID, (BaseRichBolt) Class.forName(stormConfig.getBolt().getPlugin())
                .newInstance(),Constants.STORM_PARALLEL_NUM)
                .setNumTasks(Constants.STORM_TASK_NUM)
                .shuffleGrouping(Constants.KAFKA_SPOUT_ID);
        builder.setBolt(Constants.KAFKA_RESULT_ID,resultBolt,Constants.STORM_PARALLEL_NUM)
                .setNumTasks(Constants.STORM_TASK_NUM)
                .shuffleGrouping(Constants.KAFKA_COMPARE_ID);

        NimbusClient nimbus = NimbusClient.getConfiguredClient(stormConf);
        String uploadedJarLocation = StormSubmitter.submitJar(stormConf, stormConfig.getJar().getPath());
        String jsonConf = JSONValue.toJSONString(stormConf);
        nimbus.getClient().submitTopology(taskEntity.getJobName(), uploadedJarLocation, jsonConf, builder.createTopology());

        logger.info("Task " + taskEntity.getJobName() + " submit success !!!");
        return true;
    }

}
