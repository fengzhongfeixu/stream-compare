package com.sugon.gsq.scs.service;

import com.sugon.gsq.scs.entity.LocalPasswordEntity;
import com.sugon.gsq.scs.entity.LocalUserEntity;
import com.sugon.gsq.scs.entity.UserEntity;
import com.sugon.gsq.scs.mapper.LocalPasswordMapper;
import com.sugon.gsq.scs.mapper.LocalUserMapper;
import com.sugon.gsq.scs.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by sugon on 2017/10/12.
 */
@Service
public class LocalUserService {

    @Autowired
    private LocalPasswordMapper localPasswordMapper;

    @Autowired
    private LocalUserMapper localUserMapper;

    @Autowired
    private UserMapper userMapper;

    public LocalPasswordEntity getLocalPasswordById(int id){

        LocalPasswordEntity localPasswordEntity = localPasswordMapper.selectByPrimaryKey(id);

        return  localPasswordEntity;
    }

    public LocalUserEntity getUserEntityById(int id){

        LocalUserEntity localUserEntity = localUserMapper.selectByPrimaryKey(id);

        return localUserEntity;
    }

    public UserEntity getUserEntityById(String id){

        UserEntity userEntity = userMapper.selectByPrimaryKey(id);

        return userEntity;

    }

}
