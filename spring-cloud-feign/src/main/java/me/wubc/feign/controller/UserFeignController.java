package me.wubc.feign.controller;

import me.wubc.feign.entity.ResponseResult;
import me.wubc.feign.entity.UserPo;
import me.wubc.feign.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.handler.UserRoleAuthorizationInterceptor;

/**
 * @author wbc
 * @date 2020/05/20
 * @desc
 **/
@RestController
@RequestMapping("/user")
public class UserFeignController {

    @Autowired
    private UserService userService;

    @GetMapping("/getByUsername")
    ResponseResult<UserPo> getByUsername(@RequestParam String username) {
        return userService.getByUsername(username);
    }


}
