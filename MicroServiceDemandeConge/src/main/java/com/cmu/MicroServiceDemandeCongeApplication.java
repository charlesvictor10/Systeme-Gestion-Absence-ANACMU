package com.cmu;

import com.cmu.demandeConge.config.AppProperties;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@EnableDiscoveryClient
@SpringBootApplication
@EnableConfigurationProperties(AppProperties.class)
public class MicroServiceDemandeCongeApplication {
    public static void main(String[] args) {
        SpringApplication.run(MicroServiceDemandeCongeApplication.class, args);
    }
}
