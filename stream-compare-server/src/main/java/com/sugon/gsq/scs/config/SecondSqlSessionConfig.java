package com.sugon.gsq.scs.config;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/*
 * ClassName: SecondSqlSessionConfig
 * Author: gsq
 * Date: 2019/4/9 17:19
 */
@Configuration
@MapperScan(basePackages = {"com.sugon.gsq.scs.dao"}, sqlSessionTemplateRef = "sstl02")
public class SecondSqlSessionConfig {

    @Bean(name = "sstl02")
    public SqlSessionTemplate sqlSessionTemplate(@Qualifier(value = "ssf02") SqlSessionFactory ssf02 ) throws Exception {
        SqlSessionTemplate template = new SqlSessionTemplate(ssf02);
        return template;
    }

}
