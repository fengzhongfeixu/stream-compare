package com.sugon.gsq.scs.controller;

import com.github.pagehelper.PageInfo;
import com.sugon.gsq.scs.entity.stream.StreamBlacklistSourceEntity;
import com.sugon.gsq.scs.entity.stream.StreamKafkaSourceEntity;
import com.sugon.gsq.scs.entity.stream.StreamStormTaskEntity;
import com.sugon.gsq.scs.service.StreamTaskCommandService;
import com.sugon.gsq.scs.service.StreamTaskMetadataService;
import io.swagger.annotations.ApiOperation;
import lombok.Data;
import lombok.experimental.Accessors;
import org.apache.storm.thrift.TException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;

/**
 * 功能描述:〈解析http请求，调用对应service〉
 * @Author: wy
 * @Date: 2019/4/3 9:29
 */
@RestController
@RequestMapping(value = "/api")
public class StreamTaskController {

    @Autowired
    private StreamTaskMetadataService streamTaskService;

    @Autowired
    private StreamTaskCommandService streamTaskCommandService;

    private static final Logger logger = LoggerFactory.getLogger(StreamTaskController.class);

    /*
     *   Kafka 比对源相关操作
     */

    @ApiOperation("添加kafka对比源")
    @PostMapping(value="/storm_kafka_source")
    public Object addKafkaSource(@Validated @RequestBody StreamKafkaSourceEntity skse) {
        return new ResultModel(streamTaskService.addKafkaSource(skse));
    }

    @ApiOperation("删除kafka对比源")
    @DeleteMapping(value="/storm_kafka_source/{id}")
    public Object deleteKafkaSourceById(@PathVariable String id) {
        return new ResultModel(streamTaskService.deleteKafkaSourceById(id));
    }

    @ApiOperation("修改kafka对比源元数据信息")
    @PutMapping(value="/storm_kafka_source")
    public Object updateKafkaSourceById(@RequestBody StreamKafkaSourceEntity skse) {
        return new ResultModel(streamTaskService.updateKafkaSourceById(skse));
    }

    @ApiOperation("根据id获取当前用户下的kafka对比源")
    @GetMapping(value="/storm_kafka_source/{id}")
    public Object getKafkaSourceById(@PathVariable String id) {
        return new ResultModel(streamTaskService.getKafkaSourceById(id));
    }

    @ApiOperation("获取当前用户下所有的kafka对比源")
    @GetMapping(value="/storm_kafka_source")
    public Object getAllKafkaSource(int pageNum, int pageSize) {
        PageInfo<StreamKafkaSourceEntity> pageInfo = streamTaskService.getAllKafkaSource(pageNum,pageSize);
        return new ResponseEntity<>(pageInfo, HttpStatus.OK);
    }

    /*
     *   黑名单 相关操作
     */

    @ApiOperation("添加黑名单")
    @PostMapping(value="/storm_blacklist_source")
    public Object addBlacklistSource(@Validated @RequestBody StreamBlacklistSourceEntity sbse) {
        return new ResultModel(streamTaskService.addBlacklistSource(sbse));
    }

    @ApiOperation("删除黑名单")
    @DeleteMapping(value="/storm_blacklist_source/{id}")
    public Object deleteBlacklistById(@PathVariable String id) {
        return new ResultModel(streamTaskService.deleteBlacklistById(id));
    }

    @ApiOperation("修改黑名单元数据信息")
    @PutMapping(value="/storm_blacklist_source")
    public Object updateBlacklistSourceById(@RequestBody StreamBlacklistSourceEntity sbse){
        return new ResultModel(streamTaskService.updateBlacklistSourceById(sbse));
    }

    @ApiOperation("根据id获取当前用户下的黑名单")
    @GetMapping(value="/storm_blacklist_source/{id}")
    public Object getBlacklistSourceById(@PathVariable String id) {
        return new ResultModel(streamTaskService.getBlacklistSourceById(id));
    }

    @ApiOperation("获取当前用户下所有的黑名单")
    @GetMapping(value="/storm_blacklist_source")
    public Object getAllBlacklistSource(int pageNum, int pageSize) {
        PageInfo<StreamBlacklistSourceEntity> pageInfo = streamTaskService.getAllBlacklistSource(pageNum,pageSize);
        return new ResponseEntity<>(pageInfo, HttpStatus.OK);
    }

    /*
     *   stream任务 相关操作
     */

    @ApiOperation("添加流比对任务")
    @PostMapping(value="/storm_task_source")
    public Object addStormCompareTask(@Validated @RequestBody StreamStormTaskEntity sste) {
        return new ResultModel(streamTaskService.addStormCompareTask(sste));
    }

    @ApiOperation("删除流比对任务")
    @DeleteMapping(value="/storm_task_source/{id}")
    public Object deleteStormCompareTaskById(@PathVariable String id) throws TException {
        return new ResultModel(streamTaskService.deleteStormCompareTaskById(id));
    }

    @ApiOperation("修改流比对任务元数据信息")
    @PutMapping(value="/storm_task_source")
    public Object updateStormCompareTaskById(@RequestBody StreamStormTaskEntity sste){
        return new ResultModel(streamTaskService.updateStormCompareTaskById(sste));
    }

    @ApiOperation("根据id获取当前用户下的流比对任务")
    @GetMapping(value="/storm_task_source/{id}")
    public Object getStormCompareTaskById(@PathVariable String id) throws Exception {
        return new ResultModel(streamTaskService.getStormCompareTaskById(id));
    }

    @ApiOperation("获取当前用户下所有的流比对任务")
    @GetMapping(value="/storm_task_source")
    public Object getAllStormCompareTask(int pageNum, int pageSize) throws Exception {
        PageInfo<StreamStormTaskEntity> pageInfo = streamTaskService.getAllStormCompareTask(pageNum,pageSize);
        return new ResponseEntity<>(pageInfo, HttpStatus.OK);
    }

    /*
     *   stream任务 应用相关操作
     */

    @ApiOperation("获取黑名单表的元数据信息")
    @PostMapping(value="/storm_getBlacklist_metadata")
    public Object getTableMetadata(@RequestBody StreamBlacklistSourceEntity sbse) throws SQLException, ClassNotFoundException {
        return new ResponseEntity<>(streamTaskCommandService.getMetadataOfTable(sbse),HttpStatus.OK);
    }

    @ApiOperation("测试黑名单jdbc是否正确")
    @PostMapping(value="/storm_test_jdbcConnection")
    public Object testJdbcConnection(@RequestBody StreamBlacklistSourceEntity sbse) {
        return new ResponseEntity<>(streamTaskCommandService.testJdbcConnection(sbse),HttpStatus.OK);
    }

    @ApiOperation("判断数据库表是否存在")
    @PostMapping(value="/storm_table_existOrNot")
    public Object tableExistOrNot(@RequestBody StreamBlacklistSourceEntity sbse) {
        return new ResponseEntity<>(streamTaskCommandService.tableExistOrNot(sbse),HttpStatus.OK);
    }

    @ApiOperation("根据id停止流比对任务")
    @PostMapping(value="/storm_kill_task/{id}")
    public Object killTask(@PathVariable String id) throws TException {
        return new ResultModel(streamTaskCommandService.killTaskById(id));
    }

    @ApiOperation("提交流比对任务")
    @PostMapping(value="/storm_submit_task/{id}")
    public Object submitStormTask(@PathVariable String id) throws Exception {
        return new ResultModel(streamTaskCommandService.submitCompareTask(id));
    }

    @Data
    @Accessors(chain = true)
    public static class ResultModel {
        private Object result;
        public ResultModel(Object result) {
            this.result = result;
        }
    }

}
