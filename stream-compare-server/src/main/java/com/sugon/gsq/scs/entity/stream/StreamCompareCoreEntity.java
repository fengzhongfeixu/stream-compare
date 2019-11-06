package com.sugon.gsq.scs.entity.stream;

import lombok.Data;
import lombok.ToString;
import lombok.experimental.Accessors;

import java.io.Serializable;

@Data
@ToString
@Accessors(chain = true)
public class StreamCompareCoreEntity implements Serializable {
    /**
     * 唯一标识
     */
    private String id;

    /**
     * 流比对任务id（外键）
     */
    private String stormTaskSource;

    /**
     * 黑名单源id（外键）
     */
    private String blacklistSource;

    /**
     * kafka对比字段
     */
    private String compareField;

    /**
     * 黑名单对比字段
     */
    private String compareFieldOfJdbc;

    /**
     * 对比顺序
     */
    private Integer sign;

    private static final long serialVersionUID = 1L;

}