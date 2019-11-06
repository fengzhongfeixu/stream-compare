package com.sugon.gsq.scs.authorization.security;

import com.sugon.gsq.scs.entity.IdentifyUserEntity;
import com.sugon.gsq.scs.service.IdentifyUserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.HashSet;
import java.util.Locale;
import java.util.Set;

/**
 * 从数据库获取认证用户
 */
@Component("userDetailsService")
public class DomainUserDetailsService implements UserDetailsService {

    private final Logger log = LoggerFactory.getLogger(DomainUserDetailsService.class);

    private final IdentifyUserService memberService;

    public DomainUserDetailsService(IdentifyUserService memberService) {
        this.memberService = memberService;
    }

    @Override
    @Transactional
    public UserDetails loadUserByUsername(final String login) {
        log.debug("Authenticating {}", login);
        String lowercaseLogin = login.toLowerCase(Locale.ENGLISH);
        IdentifyUserEntity memberEntity = memberService.findByUsername(lowercaseLogin);
        Collection<GrantedAuthority> grantedAuths = obtionGrantedAuthorities(memberEntity);
        return new org.springframework.security.core.userdetails.User(
                memberEntity.getUserName(), memberEntity.getPassWord(),grantedAuths);
    }

    /**
     * 获取用户权限信息
     * @param user
     * @return
     */
    private Set<GrantedAuthority> obtionGrantedAuthorities(IdentifyUserEntity user) {
//        List<Role> roles = Lists.newArrayList();
        Set<GrantedAuthority> authSet = new HashSet<>();
//        for (Role res : roles) {
//            // 用户可以访问的资源名称（或者说用户所拥有的权限） 注意：必须"ROLE_"开头
//            authSet.add(new SimpleGrantedAuthority(res.getRoleName()));
//        }
        authSet.add(new SimpleGrantedAuthority("ROLE_ADMIN"));
        return authSet;
    }

}
