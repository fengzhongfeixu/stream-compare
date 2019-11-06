package com.sugon.gsq.scs.dao;

import com.sugon.gsq.scs.entity.stream.StreamBlacklistSourceEntity;
import com.sugon.gsq.scs.entity.stream.StreamBlacklistSourceEntityExample;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StreamBlacklistSourceDAO {
    long countByExample(StreamBlacklistSourceEntityExample example);

    int deleteByExample(StreamBlacklistSourceEntityExample example);

    int deleteByPrimaryKey(String id);

    int insert(StreamBlacklistSourceEntity record);

    int insertSelective(StreamBlacklistSourceEntity record);

    List<StreamBlacklistSourceEntity> selectByExample(StreamBlacklistSourceEntityExample example);

    StreamBlacklistSourceEntity selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") StreamBlacklistSourceEntity record, @Param("example") StreamBlacklistSourceEntityExample example);

    int updateByExample(@Param("record") StreamBlacklistSourceEntity record, @Param("example") StreamBlacklistSourceEntityExample example);

    int updateByPrimaryKeySelective(StreamBlacklistSourceEntity record);

    int updateByPrimaryKey(StreamBlacklistSourceEntity record);
}