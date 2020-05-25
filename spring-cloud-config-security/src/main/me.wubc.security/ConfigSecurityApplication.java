package main.me.wubc.security;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.config.server.EnableConfigServer;

/**
 * @author wbc
 * @date 2020/05/22
 * @desc 带安全验证的配置中心
 **/

@EnableConfigServer
@EnableDiscoveryClient
@SpringBootApplication
public class ConfigSecurityApplication {

    public static void main(String[] args) {
        SpringApplication.run(ConfigSecurityApplication.class, args);
    }
}
