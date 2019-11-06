package com.sugon.gsq.scs.controller;

import com.sugon.gsq.scs.authorization.security.SecurityUtils;
import com.sugon.gsq.scs.entity.IdentifyUserEntity;
import com.sugon.gsq.scs.service.IdentifyUserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.Optional;

/**
 * REST controller for managing the current user's account.
 */
@RestController
@RequestMapping("/api")
public class AccountResource {


    private final Logger log = LoggerFactory.getLogger(AccountResource.class);

    private final IdentifyUserService memberService;

    public AccountResource(IdentifyUserService memberService) {
        this.memberService = memberService;
    }

    @GetMapping("/account")
    public ResponseEntity<IdentifyUserEntity> getAccount() {
        String userName = SecurityUtils.getCurrentUserLogin();
        return Optional.ofNullable(memberService.findByUsername(userName))
                .map(memberEntity -> new ResponseEntity<>(memberEntity, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR));
    }

    /**
     * GET  /authenticate : check if the user is authenticated, and return its login.
     *
     * @param request the HTTP request
     * @return the login if the user is authenticated
     */
    @GetMapping("/authenticated")
    public String isAuthenticated(HttpServletRequest request) {
        log.debug("REST request to check if the current user is authenticated");
        return request.getRemoteUser();
    }

}
