package com.sugon.gsq.scs.config;

import com.sugon.gsq.scs.authorization.resolvers.CurrentUserMethodArgumentResolver;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import java.util.List;

/**
 * 配置类，增加自定义拦截器和解析器
 * @see com.sugon.gsq.scs.authorization.resolvers.CurrentUserMethodArgumentResolver
 * @author sugon
 * @date 2017/9/03.
 */
@Configuration
public class MvcConfig extends WebMvcConfigurerAdapter {

    @Bean
    public CurrentUserMethodArgumentResolver currentUserMethodArgumentResolver() {
        return new CurrentUserMethodArgumentResolver();
    }

    @Override
    public void addArgumentResolvers(List<HandlerMethodArgumentResolver> argumentResolvers) {
        argumentResolvers.add(currentUserMethodArgumentResolver());
    }
}
