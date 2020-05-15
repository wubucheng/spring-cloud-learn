package me.wubc.security;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

/**
 * 带安全验证的注册中心
 */
@EnableEurekaServer
@SpringBootApplication
public class SpringCloudEurekaSecurityApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringCloudEurekaSecurityApplication.class, args);
    }

}
