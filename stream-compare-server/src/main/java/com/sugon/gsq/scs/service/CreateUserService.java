package com.sugon.gsq.scs.service;

import com.sugon.gsq.scs.entity.LocalPasswordEntity;
import com.sugon.gsq.scs.entity.LocalUserEntity;
import com.sugon.gsq.scs.entity.UserEntity;
import com.sugon.gsq.scs.mapper.LocalPasswordMapper;
import com.sugon.gsq.scs.mapper.LocalUserMapper;
import com.sugon.gsq.scs.mapper.UserMapper;
import com.sugon.gsq.scs.utils.Sha512Crypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.UUID;

/**
 * Created by sugon on 2017/10/13.
 */
@Service
public class CreateUserService {
    @Autowired
    private UserMapper userMapper;

    @Autowired
    private LocalUserMapper localUserMapper;

    @Autowired
    private LocalPasswordMapper localPasswordMapper;



    public void createUser(String projectId){

        UserEntity userEntity = new UserEntity();

        userEntity.setId(UUID.randomUUID().toString().replaceAll("-", ""));

        userEntity.setDefaultProjectId(projectId);

        userEntity.setCreatedAt(new Date());

        userMapper.insert(userEntity);

    }

    public int createLocalUser(String user_id,String userName){

        LocalUserEntity localUserEntity = new LocalUserEntity();

        localUserEntity.setUserId(user_id);

        localUserEntity.setName(userName);

        localUserMapper.insert(localUserEntity);

        return  localUserEntity.getId();
    }

    public void createPassWord(int local_user_id,String passWord){

        LocalPasswordEntity localPasswordEntity = new LocalPasswordEntity();

        localPasswordEntity.setLocalUserId(local_user_id);

        localPasswordEntity.setPassword(Sha512Crypt.Sha512_crypt(passWord,"",10000));

        localPasswordEntity.setCreatedAt(new Date());

        localPasswordMapper.insert(localPasswordEntity);
    }



   /* @Transactional
    public void CreateOsUser(String name,String passWord){

        ProjectEntityWithBLOBs projectEntityWithBLOBs = new ProjectEntityWithBLOBs();

        projectEntityWithBLOBs.setId(UUID.randomUUID().toString().replaceAll("-", ""));

        projectEntityWithBLOBs.setName(name);

        projectMapper.insert(projectEntityWithBLOBs);


        UserEntity userEntity = new UserEntity();

        userEntity.setId(UUID.randomUUID().toString().replaceAll("-", ""));

        userEntity.setDefaultProjectId(projectEntityWithBLOBs.getId());

        userEntity.setCreatedAt(new Date());

        userMapper.insert(userEntity);



        LocalUserEntity localUserEntity = new LocalUserEntity();

        localUserEntity.setUserId(userEntity.getId());

        localUserEntity.setName(name);

        localUserMapper.insert(localUserEntity);


        LocalPasswordEntity localPasswordEntity = new LocalPasswordEntity();

        localPasswordEntity.setLocalUserId(localUserEntity.getId());

        localPasswordEntity.setPassword(Sha512Crypt.Sha512_crypt(passWord,"",10000));

        localPasswordEntity.setCreatedAt(new Date());

        localPasswordMapper.insert(localPasswordEntity);

        AssignmentEntity assignmentEntity = new AssignmentEntity();

        assignmentEntity.setActorId(userEntity.getId());

        assignmentEntity.setTargetId(projectEntityWithBLOBs.getId());

        assignmentEntity.setRoleId("a66bdc3321204d00959fa3ff21afd8a7");

        assignmentMapper.insert(assignmentEntity);

    }*/
}
