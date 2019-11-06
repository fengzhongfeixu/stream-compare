package com.sugon.gsq.scs.service;

import com.alibaba.fastjson.JSON;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.sugon.gsq.scs.authorization.security.SecurityUtils;
import com.sugon.gsq.scs.config.StormConfig;
import com.sugon.gsq.scs.dao.StreamBlacklistSourceDAO;
import com.sugon.gsq.scs.dao.StreamCompareCoreDAO;
import com.sugon.gsq.scs.dao.StreamKafkaSourceDAO;
import com.sugon.gsq.scs.dao.StreamStormTaskDAO;
import com.sugon.gsq.scs.entity.stream.*;
import com.sugon.gsq.scs.utils.CommonUtil;
import com.sugon.gsq.scs.utils.StreamUtil;
import org.apache.storm.thrift.TException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

@Service
public class StreamTaskMetadataService {

    @Autowired
    private StormConfig stormConfig;

    @Autowired
    private StreamStormTaskDAO stormTaskDAO;

    @Autowired
    private StreamBlacklistSourceDAO blacklistSourceDAO;

    @Autowired
    private StreamCompareCoreDAO compareCoreDAO;

    @Autowired
    private StreamKafkaSourceDAO kafkaSourceDAO;

    private static final Logger logger = LoggerFactory.getLogger(StreamTaskMetadataService.class);

    /*
     *  kafka 源操作
     */

    public boolean addKafkaSource(StreamKafkaSourceEntity skse){
        skse.setId(CommonUtil.getUUID())
                .setBelongUser(SecurityUtils.getCurrentUserLogin())
                .setCreateDate(new Date())
                .setUpdateDate(new Date());
        return kafkaSourceDAO.insert(skse)==1 ? true : false;
    }

    public boolean deleteKafkaSourceById(String id){
        return kafkaSourceDAO.deleteByPrimaryKey(id)==1 ? true : false;
    }

    public boolean updateKafkaSourceById(StreamKafkaSourceEntity skse){
        //不能修改拥有者、修改的时间、创建的时间
        skse.setBelongUser(SecurityUtils.getCurrentUserLogin())
                .setUpdateDate(new Date())
                .setCreateDate(kafkaSourceDAO.selectByPrimaryKey(skse.getId()).getCreateDate());
        return kafkaSourceDAO.updateByPrimaryKey(skse)==1 ? true : false;
    }

    public StreamKafkaSourceEntity getKafkaSourceById(String id){
        return kafkaSourceDAO.selectByPrimaryKey(id);
    }

    public PageInfo<StreamKafkaSourceEntity> getAllKafkaSource(int pageNum, int pageSize){
        PageHelper.startPage(pageNum, pageSize);
        StreamKafkaSourceEntityExample example = new StreamKafkaSourceEntityExample();
        example.createCriteria().andBelongUserEqualTo(SecurityUtils.getCurrentUserLogin());
        List<StreamKafkaSourceEntity> list = kafkaSourceDAO.selectByExample(example);
        return new PageInfo<>(list);
    }

    /*
     *  黑名单 操作
     */

    public boolean addBlacklistSource(StreamBlacklistSourceEntity sbse){
        sbse.setId(CommonUtil.getUUID())
            .setBelongUser(SecurityUtils.getCurrentUserLogin())
            .setCreateDate(new Date())
            .setUpdateDate(new Date());
        return blacklistSourceDAO.insert(sbse)==1 ? true : false;
    }

    public boolean deleteBlacklistById(String id){
        return blacklistSourceDAO.deleteByPrimaryKey(id)==1 ? true : false;
    }

    public boolean updateBlacklistSourceById(StreamBlacklistSourceEntity sbse){
        //不能修改拥有者、修改的时间、创建的时间
        sbse.setBelongUser(SecurityUtils.getCurrentUserLogin())
                .setUpdateDate(new Date())
                .setCreateDate(blacklistSourceDAO.selectByPrimaryKey(sbse.getId()).getCreateDate());
        return blacklistSourceDAO.updateByPrimaryKey(sbse)==1 ? true : false;
    }

    public StreamBlacklistSourceEntity getBlacklistSourceById(String id){
        return blacklistSourceDAO.selectByPrimaryKey(id);
    }

    public PageInfo<StreamBlacklistSourceEntity> getAllBlacklistSource(int pageNum, int pageSize){
        PageHelper.startPage(pageNum, pageSize);
        StreamBlacklistSourceEntityExample example = new StreamBlacklistSourceEntityExample();
        example.createCriteria().andBelongUserEqualTo(SecurityUtils.getCurrentUserLogin());
        List<StreamBlacklistSourceEntity> list = blacklistSourceDAO.selectByExample(example);
        return new PageInfo<>(list);
    }

    /*
     *  stream 任务操作
     */

    @Transactional(transactionManager = "tm02")
    public boolean addStormCompareTask(StreamStormTaskEntity sste){
        sste.setId(CommonUtil.getUUID())
                .setJobName(SecurityUtils.getCurrentUserLogin()+ "_" + sste.getJobName())
                .setBelongUser(SecurityUtils.getCurrentUserLogin())
                .setCreateDate(new Date())
                .setUpdateDate(new Date());

        //如果回推字段(keys)中没有比对字段就添加
        for(Map<String, Object> entity : sste.getStormCompareCores()){
            for(String field : entity.get("compareField").toString().split(",")){
                boolean flag = true;
                loop:
                for(String key : sste.getKeys().split(",")){
                    if(key.equals(field)){
                        flag = false;
                        break loop;
                    }
                }
                if(flag){
                    sste.setKeys(sste.getKeys() + "," + field);
                }
            }
        }

        //判断任务名是否重复
        StreamStormTaskEntityExample example = new StreamStormTaskEntityExample();
        example.createCriteria().andJobNameEqualTo(sste.getJobName());
        if(stormTaskDAO.selectByExample(example).size()!=0){
            return false;
        }

        //参数分俩部分存储
        if(stormTaskDAO.insert(sste) == 1){
            for(int i=0 ; i<sste.getStormCompareCores().size() ; i++){
                Map<String, Object> core = sste.getStormCompareCores().get(i);
                // 转化成可以入库的数据类型
                StreamCompareCoreEntity scce = JSON.parseObject(JSON.toJSONString(core),
                        StreamCompareCoreEntity.class);
                // 添加必要的属性
                scce.setId(CommonUtil.getUUID())
                        .setStormTaskSource(sste.getId())
                        .setSign(i+1);
                if(compareCoreDAO.insert(scce)!=1){
                    throw new RuntimeException("Compare cores save failure !!!");
                }
            }
        } else {
            return false;
        }

        return true;
    }

    @Transactional(transactionManager = "tm02")
    public boolean deleteStormCompareTaskById(String id) throws TException {
        StreamStormTaskEntity sste = stormTaskDAO.selectByPrimaryKey(id);
        //判断任务是否运行中
        if(StreamUtil.isRunning(sste.getJobName(),stormConfig.getNimbus().getNodes())){
            return false;
        }
        if(stormTaskDAO.deleteByPrimaryKey(sste.getId()) == 1){
            StreamCompareCoreEntityExample example = new StreamCompareCoreEntityExample();
            example.createCriteria().andStormTaskSourceEqualTo(sste.getId());
            if(compareCoreDAO.deleteByExample(example) == 0){
                throw new RuntimeException("Compare cores delete failure !!!");
            }
            return true;
        }
        return false;
    }

    @Transactional(transactionManager = "tm02")
    public boolean updateStormCompareTaskById(StreamStormTaskEntity sste){
        //不能修改拥有者、修改的时间、创建的时间
        sste.setJobName(SecurityUtils.getCurrentUserLogin() + "_" + sste.getJobName())
                .setBelongUser(SecurityUtils.getCurrentUserLogin())
                .setUpdateDate(new Date())
                .setCreateDate(stormTaskDAO.selectByPrimaryKey(sste.getId()).getCreateDate());

        //如果回推字段没有比对字段就添加
        for(Map<String, Object> entity : sste.getStormCompareCores()){
            for(String field : entity.get("compareField").toString().split(",")){
                boolean flag = true;
                loop:
                for(String key : sste.getKeys().split(",")){
                    if(key.equals(field)){
                        flag = false;
                        break loop;
                    }
                }
                if(flag){
                    sste.setKeys(sste.getKeys() + "," + field);
                }
            }
        }

        //修改stream任务元数据
        if(stormTaskDAO.updateByPrimaryKey(sste) == 1){
            //删除以前的cores
            StreamCompareCoreEntityExample example = new StreamCompareCoreEntityExample();
            example.createCriteria().andStormTaskSourceEqualTo(sste.getId());
            if(compareCoreDAO.deleteByExample(example) == 0){
                throw new RuntimeException("Compare cores delete failure !!!");
            }
            //重新添加
            for(int i=0 ; i<sste.getStormCompareCores().size() ; i++){
                Map<String, Object> core = sste.getStormCompareCores().get(i);
                // 转化成可以入库的数据类型
                StreamCompareCoreEntity scce = JSON.parseObject(JSON.toJSONString(core),
                        StreamCompareCoreEntity.class);
                // 添加必要的属性
                scce.setId(CommonUtil.getUUID())
                        .setStormTaskSource(sste.getId())
                        .setSign(i+1);
                if(compareCoreDAO.insert(scce) != 1){
                    throw new RuntimeException("Compare cores insert failure !!!");
                }
            }
            return true;
        }
        return false;
    }

    public StreamStormTaskEntity getStormCompareTaskById(String id) throws Exception {
        // 根据taskid获取任务基本信息
        StreamStormTaskEntity stormTaskEntity = stormTaskDAO.selectByPrimaryKey(id);
        if(stormTaskEntity == null) return null;
        // 根据tadkid获取cores
        StreamCompareCoreEntityExample compareCoreEntityExample = new StreamCompareCoreEntityExample();
        compareCoreEntityExample.createCriteria().andStormTaskSourceEqualTo(stormTaskEntity.getId());
        List<StreamCompareCoreEntity> compareCoreEntities = compareCoreDAO.selectByExample(compareCoreEntityExample);
        List<Map<String, Object>> coresResult = new LinkedList<>();
        for(StreamCompareCoreEntity compareCoreEntity : compareCoreEntities){
            Map<String, Object> mergeMaps = CommonUtil.getFieldValue(blacklistSourceDAO.selectByPrimaryKey(compareCoreEntity.getBlacklistSource()),
                    compareCoreEntity);
            coresResult.add(mergeMaps);
        }
        // 根据kafkaid获取kafkasource
        StreamKafkaSourceEntity kafkaSourceEntity = kafkaSourceDAO.selectByPrimaryKey(stormTaskEntity.getKafkaSource());
        // 获取运行状态
        String status = StreamUtil.isRunning(stormTaskEntity.getJobName(),stormConfig.getNimbus().getNodes())?"active":"killed";
        // 组装返回结果
        stormTaskEntity.setStormCompareCores(coresResult)
                // 任务名称去掉用户名
                .setJobName(StreamUtil.prefixUser(stormTaskEntity.getJobName()))
                .setStormKafkaSourceEntity(kafkaSourceEntity)
                .setStatus(status);
        return stormTaskEntity;
    }

    public PageInfo<StreamStormTaskEntity> getAllStormCompareTask(int pageNum, int pageSize) throws Exception {
        PageHelper.startPage(pageNum, pageSize);
        // 获取用户下的所有task
        StreamStormTaskEntityExample stormTaskEntityExample = new StreamStormTaskEntityExample();
        stormTaskEntityExample.createCriteria().andBelongUserEqualTo(SecurityUtils.getCurrentUserLogin());
        List<StreamStormTaskEntity> stormTaskEntities = stormTaskDAO.selectByExample(stormTaskEntityExample);
        // 扫描所有task
        for(StreamStormTaskEntity stormTaskEntity : stormTaskEntities){
            // 根据tadkid获取cores
            StreamCompareCoreEntityExample compareCoreEntityExample = new StreamCompareCoreEntityExample();
            compareCoreEntityExample.createCriteria().andStormTaskSourceEqualTo(stormTaskEntity.getId());
            List<StreamCompareCoreEntity> compareCoreEntities = compareCoreDAO.selectByExample(compareCoreEntityExample);
            List<Map<String, Object>> coresResult = new LinkedList<>();
            for(StreamCompareCoreEntity compareCoreEntity : compareCoreEntities){
                Map<String, Object> mergeMaps = CommonUtil.getFieldValue(blacklistSourceDAO.selectByPrimaryKey(compareCoreEntity.getBlacklistSource()),
                        compareCoreEntity);
                coresResult.add(mergeMaps);
            }
            // 根据kafkaid获取kafkasource
            StreamKafkaSourceEntity kafkaSourceEntity = kafkaSourceDAO.selectByPrimaryKey(stormTaskEntity.getKafkaSource());
            // 获取运行状态
            String status = StreamUtil.isRunning(stormTaskEntity.getJobName(),
                    stormConfig.getNimbus().getNodes())?"active":"killed";
            // 组装返回结果
            stormTaskEntity.setStormCompareCores(coresResult)
                    // 任务名称去掉用户名
                    .setJobName(StreamUtil.prefixUser(stormTaskEntity.getJobName()))
                    .setStormKafkaSourceEntity(kafkaSourceEntity)
                    .setStatus(status);
        }
        return new PageInfo<>(stormTaskEntities);
    }

}
