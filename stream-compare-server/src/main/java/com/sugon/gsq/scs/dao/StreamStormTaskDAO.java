package com.sugon.gsq.scs.dao;

import com.sugon.gsq.scs.entity.stream.StreamStormTaskEntity;
import com.sugon.gsq.scs.entity.stream.StreamStormTaskEntityExample;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StreamStormTaskDAO {
    long countByExample(StreamStormTaskEntityExample example);

    int deleteByExample(StreamStormTaskEntityExample example);

    int deleteByPrimaryKey(String id);

    int insert(StreamStormTaskEntity record);

    int insertSelective(StreamStormTaskEntity record);

    List<StreamStormTaskEntity> selectByExample(StreamStormTaskEntityExample example);

    StreamStormTaskEntity selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") StreamStormTaskEntity record, @Param("example") StreamStormTaskEntityExample example);

    int updateByExample(@Param("record") StreamStormTaskEntity record, @Param("example") StreamStormTaskEntityExample example);

    int updateByPrimaryKeySelective(StreamStormTaskEntity record);

    int updateByPrimaryKey(StreamStormTaskEntity record);
}