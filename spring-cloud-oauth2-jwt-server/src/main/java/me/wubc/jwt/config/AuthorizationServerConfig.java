package me.wubc.jwt.config;


import me.wubc.jwt.ext.JwtTokenEnhancer;
import me.wubc.jwt.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.TokenEnhancer;
import org.springframework.security.oauth2.provider.token.TokenEnhancerChain;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;

import java.util.ArrayList;

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

    @Qualifier("redisTokenStore")
    @Autowired
    private TokenStore tokenStore;

    @Autowired
    private JwtAccessTokenConverter jwtAccessTokenConverter;
    @Autowired
    private JwtTokenEnhancer jwtTokenEnhancer;

    @Override
    public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
        TokenEnhancerChain tokenEnhancerChain = new TokenEnhancerChain();
        ArrayList<TokenEnhancer> delegates = new ArrayList<>();
        delegates.add(jwtAccessTokenConverter);
        delegates.add(jwtTokenEnhancer);
        tokenEnhancerChain.setTokenEnhancers(delegates);

        endpoints.authenticationManager(authenticationManager)
                .userDetailsService(userService)
                .tokenStore(tokenStore)
                .accessTokenConverter(jwtAccessTokenConverter)
                .tokenEnhancer(tokenEnhancerChain);


    }

    /**
     * http://localhost:8077/oauth/authorize?response_type=code
     * &client_id=admin&redirect_uri=http://www.baidu.com&scope=all&state=normal
     *
     * @param clients
     * @throws Exception 配置后，游览器输入上面的地址即可跳到授权页面
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
                .redirectUris("http://localhost:8079/login")
                // 自动授权
                .autoApprove(true)
                // 权限范围
                .scopes("all")
                // 授权类型
                .authorizedGrantTypes("authorization_code", "password", "refresh_token");
    }

    public void configure(AuthorizationServerSecurityConfigurer security) throws Exception {
        // 获取秘钥需要身份证认证
        security.tokenKeyAccess("isAuthenticated()");
    }


}
