package me.wubc.nacos;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @author wbc
 * @date 2020/5/25
 * @desc
 */
@EnableDiscoveryClient
@SpringBootApplication
public class NacosRibbonApplication {

    public static void main(String[] args) {
        SpringApplication.run(NacosRibbonApplication.class, args);
    }
}
