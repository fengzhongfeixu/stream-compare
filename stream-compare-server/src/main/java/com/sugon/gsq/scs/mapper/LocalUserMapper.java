package com.sugon.gsq.scs.mapper;


import com.sugon.gsq.scs.entity.LocalUserEntity;

public interface LocalUserMapper {

    int deleteByPrimaryKey(Integer id);

    int insert(LocalUserEntity record);

    int insertSelective(LocalUserEntity record);

    LocalUserEntity selectByPrimaryKey(Integer id);

    LocalUserEntity getLocalUserByName(String name);

    int updateByPrimaryKeySelective(LocalUserEntity record);

    int updateByPrimaryKey(LocalUserEntity record);
}
