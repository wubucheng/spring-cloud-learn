package me.wubc.consul.config;

import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

/**
 * @author wbc
 * @date 2020/05/15
 * @desc
 **/
@Configuration
public class RibbonConfig {

    // 使其具有负载均衡的能力
    @LoadBalanced
    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }
}
