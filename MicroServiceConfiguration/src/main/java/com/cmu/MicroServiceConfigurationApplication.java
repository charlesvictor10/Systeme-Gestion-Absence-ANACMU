package com.cmu;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.config.server.EnableConfigServer;

@EnableConfigServer
@SpringBootApplication
public class MicroServiceConfigurationApplication {

    public static void main(String[] args) {
        SpringApplication.run(MicroServiceConfigurationApplication.class, args);
    }
}
