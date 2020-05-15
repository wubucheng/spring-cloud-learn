package me.wubc.eureka;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

/**
 * @author wbc
 * @date 2020/05/14
 * @desc
 **/
@EnableEurekaServer
@SpringBootApplication
public class EurekaSpringApplication {

    public static void main(String[] args) {
        SpringApplication.run(EurekaSpringApplication.class, args);
    }

}
