package com.sugon.gsq.scs.dao;

import com.sugon.gsq.scs.entity.stream.StreamKafkaSourceEntity;
import com.sugon.gsq.scs.entity.stream.StreamKafkaSourceEntityExample;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StreamKafkaSourceDAO {
    long countByExample(StreamKafkaSourceEntityExample example);

    int deleteByExample(StreamKafkaSourceEntityExample example);

    int deleteByPrimaryKey(String id);

    int insert(StreamKafkaSourceEntity record);

    int insertSelective(StreamKafkaSourceEntity record);

    List<StreamKafkaSourceEntity> selectByExample(StreamKafkaSourceEntityExample example);

    StreamKafkaSourceEntity selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") StreamKafkaSourceEntity record, @Param("example") StreamKafkaSourceEntityExample example);

    int updateByExample(@Param("record") StreamKafkaSourceEntity record, @Param("example") StreamKafkaSourceEntityExample example);

    int updateByPrimaryKeySelective(StreamKafkaSourceEntity record);

    int updateByPrimaryKey(StreamKafkaSourceEntity record);
}