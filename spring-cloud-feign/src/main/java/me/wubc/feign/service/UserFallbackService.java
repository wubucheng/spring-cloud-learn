package me.wubc.feign.service;

import lombok.extern.slf4j.Slf4j;
import me.wubc.feign.entity.ResponseResult;
import me.wubc.feign.entity.UserPo;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author wbc
 * @date 2020/05/20
 * @desc
 **/
@Slf4j
@Component
public class UserFallbackService implements UserService {

    @Override
    public ResponseResult<UserPo> getByUsername(String username) {
        log.error("通过用户名获取用户失败,进行服务降级");
        return new ResponseResult("通过用户名获取用户失败", 500);
    }
}
