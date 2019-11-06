package com.sugon.gsq.scs.mapper;

import com.sugon.gsq.scs.entity.IdentifyUserEntity;

public interface IdentifyUserMapper {

    IdentifyUserEntity findByUsername(String userName);

  }
