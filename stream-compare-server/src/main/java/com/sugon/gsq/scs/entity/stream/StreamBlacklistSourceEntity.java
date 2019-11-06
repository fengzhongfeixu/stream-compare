package com.sugon.gsq.scs.entity.stream;

import lombok.Data;
import lombok.ToString;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.Date;

@Data
@ToString
@Accessors(chain = true)
public class StreamBlacklistSourceEntity implements Serializable {
    /**
     * 唯一标识
     */
    private String id;

    /**
     * 所属用户
     */
    private String belongUser;

    /**
     * 黑名单描述行名称
     */
    private String name;

    /**
     * 黑名单所属库的驱动名称
     */
    private String driver;

    /**
     * 黑名单库连接路径
     */
    private String url;

    /**
     * 登陆用户名
     */
    private String username;

    /**
     * 登陆密码
     */
    private String pwd;

    /**
     * 黑名单表
     */
    private String compareTableName;

    /**
     * 黑名单表来源
     */
    private String source;

    /**
     * 黑名单表元数据信息
     */
    private String metadata;

    private Date createDate;

    private Date updateDate;

    private static final long serialVersionUID = 1L;

}