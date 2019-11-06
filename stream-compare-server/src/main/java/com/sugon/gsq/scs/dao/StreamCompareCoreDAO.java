package com.sugon.gsq.scs.dao;

import com.sugon.gsq.scs.entity.stream.StreamCompareCoreEntity;
import com.sugon.gsq.scs.entity.stream.StreamCompareCoreEntityExample;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StreamCompareCoreDAO {
    long countByExample(StreamCompareCoreEntityExample example);

    int deleteByExample(StreamCompareCoreEntityExample example);

    int deleteByPrimaryKey(String id);

    int insert(StreamCompareCoreEntity record);

    int insertSelective(StreamCompareCoreEntity record);

    List<StreamCompareCoreEntity> selectByExample(StreamCompareCoreEntityExample example);

    StreamCompareCoreEntity selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") StreamCompareCoreEntity record, @Param("example") StreamCompareCoreEntityExample example);

    int updateByExample(@Param("record") StreamCompareCoreEntity record, @Param("example") StreamCompareCoreEntityExample example);

    int updateByPrimaryKeySelective(StreamCompareCoreEntity record);

    int updateByPrimaryKey(StreamCompareCoreEntity record);
}