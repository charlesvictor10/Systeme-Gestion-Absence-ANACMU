package com.cmu;

import com.cmu.admin.config.AppProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.session.jdbc.config.annotation.web.http.EnableJdbcHttpSession;

@EnableDiscoveryClient
@SpringBootApplication
@EnableJdbcHttpSession
@EnableConfigurationProperties(AppProperties.class)
public class MicroServiceAdministrationApplication {

    public static void main(String[] args) {
        SpringApplication.run(MicroServiceAdministrationApplication.class, args);
    }

}
