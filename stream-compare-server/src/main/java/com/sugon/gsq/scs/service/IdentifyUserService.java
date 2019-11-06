package com.sugon.gsq.scs.service;

import com.sugon.gsq.scs.entity.IdentifyUserEntity;
import com.sugon.gsq.scs.mapper.IdentifyUserMapper;
import org.springframework.stereotype.Service;

@Service
public class IdentifyUserService {

    private final IdentifyUserMapper identifyUserMapper;

    public IdentifyUserService(IdentifyUserMapper identifyUserMapper) {
        this.identifyUserMapper = identifyUserMapper;
    }

    public IdentifyUserEntity findByUsername(String userName) {
        return identifyUserMapper.findByUsername(userName);
    }
/*
    @Transactional
    public void save(IdentifyUserEntity memberEntity) {
        identifyUserMapper.insert(memberEntity);
    }*/

  /*  public PageInfo<IdentifyUserEntity> findByPage(int pageNum, int pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        List<IdentifyUserEntity> memberEntityList = identifyUserMapper.getAll();
        PageInfo<IdentifyUserEntity> pageInfo =new PageInfo<>(memberEntityList);
        return pageInfo;
    }*/
}
