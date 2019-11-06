package com.sugon.gsq.scs;

import com.github.pagehelper.autoconfigure.PageHelperAutoConfiguration;
import com.sugon.gsq.scs.config.StormConfig;
import org.mybatis.spring.boot.autoconfigure.MybatisAutoConfiguration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@EnableScheduling
@EnableTransactionManagement
@EnableDiscoveryClient
@SpringBootApplication(exclude = {MybatisAutoConfiguration.class, PageHelperAutoConfiguration.class})
public class Application implements CommandLineRunner {

    @Autowired
    private StormConfig stormConfig;

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Override
    public void run(String... strings) throws Exception {

    }
}
