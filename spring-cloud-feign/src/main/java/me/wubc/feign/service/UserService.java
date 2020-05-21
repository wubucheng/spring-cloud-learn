package me.wubc.feign.service;

import me.wubc.feign.entity.ResponseResult;
import me.wubc.feign.entity.UserPo;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author wbc
 * @date 2020/05/20
 * @desc
 **/
@FeignClient(value = "spring-cloud-ribbon-provider", fallback = UserFallbackService.class)
public interface UserService {

    @GetMapping("/user/getByUsername")
    ResponseResult<UserPo> getByUsername(@RequestParam String username);
}
