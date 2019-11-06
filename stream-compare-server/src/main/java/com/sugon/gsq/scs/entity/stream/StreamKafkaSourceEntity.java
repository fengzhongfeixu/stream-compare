package com.sugon.gsq.scs.entity.stream;

import lombok.Data;
import lombok.ToString;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.Date;

@Data
@ToString
@Accessors(chain = true)
public class StreamKafkaSourceEntity implements Serializable {
    /**
     * 唯一标识
     */
    private String id;

    /**
     * 所属用户
     */
    private String belongUser;

    /**
     * kafka数据源描述性名称
     */
    private String name;

    /**
     * zk地址
     */
    private String zkServer;

    /**
     * zk路径
     */
    private String zkRoot;

    /**
     * 需要监听对比的订阅号
     */
    private String topic;

    /**
     * kafka元数据信息
     */
    private String metadata;

    private Date createDate;

    private Date updateDate;

    private static final long serialVersionUID = 1L;

}