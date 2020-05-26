package me.wubc.gateway.config;

import org.springframework.cloud.gateway.filter.ratelimit.KeyResolver;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import reactor.core.publisher.Mono;

/**
 * @author wbc
 * @date 2020/05/26
 * @desc 限流策略，两者取一种
 **/
@Configuration
public class RedisRateLimiterConfig {

    /**
     * serverwebexchange这个类存储了request和response，相当于请求和响应的上下文
     */

    /**
     * 根据用户名来进行限流
     *
     * @return
     */
//    @Bean
//    public KeyResolver userKeyResolver() {
//        return exchange -> Mono.just(exchange.getRequest().getQueryParams().getFirst("username"));
//    }

    /**
     * 根据IP来限流
     *
     * @return
     */
    @Bean
    public KeyResolver ipKeyResolver() {
        return exchange -> Mono.just(exchange.getRequest().getRemoteAddress().getHostName());
    }
}
