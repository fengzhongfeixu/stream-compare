package com.sugon.gsq.scs.config;

import com.github.pagehelper.PageInterceptor;
import com.github.pagehelper.QueryInterceptor;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

import javax.sql.DataSource;
import java.util.Properties;

/*
 * ClassName: MybatisConfig
 * Author: gsq
 * Date: 2019/4/8 18:25
 */
@Configuration
public class MybatisConfig {

    @Primary
    @Bean(name = "ssf01")
    public SqlSessionFactory sqlSessionFactory(@Qualifier("metadata_db") DataSource ds) throws Exception {
        SqlSessionFactoryBean fb = new SqlSessionFactoryBean();
        fb.setDataSource(ds);
        PathMatchingResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
        fb.setMapperLocations(resolver
                .getResources("classpath*:mybatis/metadata/*.xml"));
        // mybatis的bug?
        /*fb.setConfigLocation(resolver
                .getResource("classpath*:mybatis/stream/*.xml"));*/

        PageInterceptor pageInterceptor = new PageInterceptor();
        QueryInterceptor queryInterceptor = new QueryInterceptor();
        Properties properties = new Properties();
        properties.setProperty("offsetAsPageNum","true");
        properties.setProperty("rowBoundsWithCount","true");
        properties.setProperty("reasonable","true");
        properties.setProperty("helperDialect","mysql");
        queryInterceptor.setProperties(properties);

        fb.setPlugins(new Interceptor[] {pageInterceptor,queryInterceptor});

        return fb.getObject();
    }

    @Bean(name = "ssf02")
    public SqlSessionFactory sqlSessionFactory01(@Qualifier("stream_db") DataSource ds) throws Exception {
        SqlSessionFactoryBean fb = new SqlSessionFactoryBean();
        fb.setDataSource(ds);
        fb.setMapperLocations(new PathMatchingResourcePatternResolver()
                .getResources("classpath*:mybatis/stream/*.xml"));

        PageInterceptor pageInterceptor = new PageInterceptor();
        QueryInterceptor queryInterceptor = new QueryInterceptor();
        Properties properties = new Properties();
        properties.setProperty("offsetAsPageNum","true");
        properties.setProperty("rowBoundsWithCount","true");
        properties.setProperty("reasonable","true");
        properties.setProperty("helperDialect","mysql");
        queryInterceptor.setProperties(properties);

        fb.setPlugins(new Interceptor[] {pageInterceptor,queryInterceptor});

        return fb.getObject();
    }

    @Primary
    @Bean(name = "tm01")
    public DataSourceTransactionManager transactionManager(@Qualifier("metadata_db") DataSource dataSource) throws Exception {
        return new DataSourceTransactionManager(dataSource);
    }

    @Bean(name = "tm02")
    public DataSourceTransactionManager transactionManager01(@Qualifier("stream_db") DataSource dataSource) throws Exception {
        return new DataSourceTransactionManager(dataSource);
    }

}
