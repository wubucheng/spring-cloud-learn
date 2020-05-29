package me.wubc.autho2client;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.oauth2.client.EnableOAuth2Sso;

/**
 * @author wbc
 * @date 2020/5/28
 * @desc
 */
// 开启SSO的注解
@EnableOAuth2Sso
@SpringBootApplication
public class  Oauth2ClientApplication {
    public static void main(String[] args) {
        SpringApplication.run(Oauth2ClientApplication.class, args);
    }
}
