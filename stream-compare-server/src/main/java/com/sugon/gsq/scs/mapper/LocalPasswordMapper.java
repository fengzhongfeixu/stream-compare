package com.sugon.gsq.scs.mapper;


import com.sugon.gsq.scs.entity.LocalPasswordEntity;

public interface LocalPasswordMapper {

    int deleteByPrimaryKey(Integer id);

    int insert(LocalPasswordEntity record);

    int insertSelective(LocalPasswordEntity record);

    LocalPasswordEntity selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(LocalPasswordEntity record);

    int updateByPrimaryKey(LocalPasswordEntity record);
}
