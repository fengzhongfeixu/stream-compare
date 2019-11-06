package com.sugon.gsq.scs.config;

import com.codahale.metrics.MetricRegistry;
import lombok.Data;
import lombok.ToString;
import lombok.experimental.Accessors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

/*
 * ClassName: StormConfig
 * Author: Administrator
 * Date: 2019/5/24 14:40
 */
@Data
@ToString
@Configuration
@Accessors(chain = true)
public class StormConfig {

    @Autowired
    private Nimbus nimbus;

    @Autowired
    private Jar jar;

    @Autowired
    private Bolt bolt;

    @Data
    @ToString
    @Configuration
    @Accessors(chain = true)
    @ConfigurationProperties(prefix = "storm.nimbus")
    public class Nimbus{
        private List<String> nodes;
    }

    @Data
    @ToString
    @Configuration
    @Accessors(chain = true)
    @ConfigurationProperties(prefix = "storm.jar")
    public class Jar{
        private String path;
    }

    @Data
    @ToString
    @Configuration
    @Accessors(chain = true)
    @ConfigurationProperties(prefix = "storm.bolt")
    public class Bolt{
        private String plugin;
    }

    @Bean
    public MetricRegistry getMetricRegistryEntity(){
        return new MetricRegistry();
    }

}
