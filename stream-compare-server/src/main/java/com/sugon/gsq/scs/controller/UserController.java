package com.sugon.gsq.scs.controller;

import com.sugon.gsq.scs.entity.IdentifyUserEntity;
import com.sugon.gsq.scs.service.IdentifyUserService;
import com.sugon.gsq.scs.service.LocalUserService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * 用户控制器
 * @author sugon
 * @date 2017/9/04.
 */
@RestController
@RequestMapping("/local")
public class UserController {

    @Autowired
    private IdentifyUserService identifyUserService;

    @Autowired
    private LocalUserService localUserService;

    @RequestMapping(value = "/test/{name}",method = RequestMethod.GET)
    @ApiOperation(value="测试", notes="测试")
    public ResponseEntity<IdentifyUserEntity> getLocalUserByName(@PathVariable("name") String name) {

        IdentifyUserEntity identifyUserEntity = identifyUserService.findByUsername(name);

        return new ResponseEntity<IdentifyUserEntity>(identifyUserEntity, HttpStatus.OK);

    }

}
