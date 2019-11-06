package com.sugon.gsq.scs.config;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

/*
 * ClassName: FirstSqlSessionConfig
 * Author: gsq
 * Date: 2019/4/9 17:19
 */
@Configuration
@MapperScan(basePackages = {"com.sugon.gsq.scs.mapper"}, sqlSessionTemplateRef = "sstl01")
public class FirstSqlSessionConfig {

    @Primary
    @Bean(name = "sstl01")
    public SqlSessionTemplate sqlSessionTemplate(@Qualifier(value = "ssf01") SqlSessionFactory ssf01 ) throws Exception {
        SqlSessionTemplate template = new SqlSessionTemplate(ssf01);
        return template;
    }

}
