package com.sugon.gsq.scs.utils;

/**
 * 常量
 * @author sugon
 * @date 2017/9/03.
 */
public class Constants {

    /**
     * 存储当前登录用户的字段名
     */
    public static final String CURRENT_USER_LOGIN = "CURRENT_USER_LOGIN";

    public static final Integer STORM_PARALLEL_NUM = 3; // 数据处理单元的并行数

    public static final Integer STORM_TASK_NUM = 3; // 数据处理单元的进程数

    public static final String KAFKA_SPOUT_ID = "kafka-stream"; // kafka数据源ID

    public static final String KAFKA_COMPARE_ID = "compare-bolt";   // 数据对比处理单元ID

    public static final String KAFKA_RESULT_ID = "kafka-bolt";  // 数据对比结果ID

    public static final String KAFKA_KEY_SERIALIZER = "org.apache.kafka.common.serialization.StringSerializer";

    public static final String KAFKA_VALUE_SERIALIZER = "org.apache.kafka.common.serialization.StringSerializer";

    public static final String[] COMPARE_CORE_PARAMS = {"compareTableName","driver","sign","compareField"
            ,"pwd","url","compareFieldOfJdbc","username"};  // 处理环节需要的参数列表

}
