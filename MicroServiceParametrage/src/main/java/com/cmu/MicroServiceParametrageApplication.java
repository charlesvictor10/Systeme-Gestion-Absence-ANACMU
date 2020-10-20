package com.cmu;

import com.cmu.parametrage.config.AppProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@EnableDiscoveryClient
@SpringBootApplication
@EnableConfigurationProperties(AppProperties.class)
public class MicroServiceParametrageApplication {
    public static void main(String[] args) {
        SpringApplication.run(MicroServiceParametrageApplication.class, args);
    }
}
