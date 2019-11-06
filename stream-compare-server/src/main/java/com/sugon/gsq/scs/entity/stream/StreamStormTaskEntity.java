package com.sugon.gsq.scs.entity.stream;

import lombok.Data;
import lombok.ToString;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Data
@ToString
@Accessors(chain = true)
public class StreamStormTaskEntity implements Serializable {
    /**
     * 唯一标识
     */
    private String id;

    /**
     * 所属用户
     */
    private String belongUser;

    /**
     * 任务名称
     */
    private String jobName;

    /**
     * storm-workes数量
     */
    private Integer workerNum;

    /**
     * 对比类型(外键对比类型信息表)
     */
    private String compareType;

    /**
     * kafka服务地址
     */
    private String kafkaServer;

    /**
     * kafka回写结果字段
     */
    private String result;

    /**
     * kafka数据源(外键)
     */
    private String kafkaSource;

    /**
     * 前端布局
     */
    private String layout;

    /**
     * 前端样式
     */
    private String setting;

    /**
     * 对比任务模式
     */
    private String model;

    private String keys;

    private StreamKafkaSourceEntity stormKafkaSourceEntity; // 展示需要字段

    private List<Map<String, Object>> stormCompareCores;    // 参数、展示需要字段

    private String status;  // 展示需要字段

    private Date createDate;

    private Date updateDate;

    private static final long serialVersionUID = 1L;

}