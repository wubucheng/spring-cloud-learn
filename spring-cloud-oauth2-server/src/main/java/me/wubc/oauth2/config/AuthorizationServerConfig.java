package me.wubc.oauth2.config;

import lombok.extern.slf4j.Slf4j;
import me.wubc.oauth2.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;

/**
 * @author wbc
 * @date 2020/05/27
 * @desc 授权服务器配置
 **/
@Configuration
@EnableAuthorizationServer
public class AuthorizationServerConfig extends AuthorizationServerConfigurerAdapter {

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private UserService userService;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Override
    public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
        endpoints.authenticationManager(authenticationManager)
                .userDetailsService(userService);
    }

    /**
     * http://localhost:8077/oauth/authorize?response_type=code
     * &client_id=admin&redirect_uri=http://www.baidu.com&scope=all&state=normal
     * @param clients
     * @throws Exception
     * 配置后，游览器输入上面的地址即可跳到授权页面
     */
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
        clients.inMemory()
                .withClient("admin")
                .secret(passwordEncoder.encode("123456"))
                // 设置token有效期
                .accessTokenValiditySeconds(60 * 60)
                // 设置刷新token的有效期
                .refreshTokenValiditySeconds(60 * 60 * 24)
                // 授权成功后跳转地址
                .redirectUris("http://www.baidu.com")
                // 权限范围
                .scopes("all")
                // 授权类型
                .authorizedGrantTypes("authorization_code", "password");
    }


}
