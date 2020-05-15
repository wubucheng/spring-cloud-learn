package me.wubc.ribbon.controller;

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
@RestController
@RequestMapping("/user")
public class UserController {

    @GetMapping("/getByUsername")
    public ResponseResult<UserPo> getByUsername(@RequestParam String username) {
        UserPo user = new UserPo();
        user.setName(username);
        user.setAge(18);
        return new ResponseResult<UserPo>(user);
    }


}
