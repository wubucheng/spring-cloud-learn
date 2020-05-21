package me.wubc.ribbon.controller;

import lombok.extern.slf4j.Slf4j;
import me.wubc.ribbon.entity.ResponseResult;
import me.wubc.ribbon.entity.UserPo;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author wbc
 * @date 2020/05/15
 * @desc
 **/
@Slf4j
@RestController
@RequestMapping("/user")
public class UserController {

    @GetMapping("/getByUsername")
    public ResponseResult<UserPo> getByUsername(@RequestParam String username) {
        log.info("收到调用");
        UserPo user = new UserPo();
        user.setName(username);
        user.setAge(18);
        return new ResponseResult<UserPo>(user);
    }


}
