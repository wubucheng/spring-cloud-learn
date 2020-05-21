package me.wubc.hystrix.controller;

import me.wubc.hystrix.entity.ResponseResult;
import me.wubc.hystrix.entity.UserPo;
import me.wubc.hystrix.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

/**
 * @author wbc
 * @date 2020/05/18
 * @desc
 **/
@RestController
@RequestMapping("/user")
public class UserHystrixController {

    @Autowired
    private UserService userService;

    @RequestMapping("getByUsername")
    public ResponseResult getByUsername(@RequestParam String username) {
        return userService.getByUsername(username);
    }

    @RequestMapping("testCache")
    public void testCache() {
        userService.testCache("hello world");
        userService.testCache("hello world");
        userService.testCache("hello world");
    }

    @RequestMapping("testColl")
    public void testColl() throws ExecutionException, InterruptedException {
        Future<UserPo> hello = userService.getUser("hello");
        Future<UserPo> world = userService.getUser("world");
//        hello.get();
//        world.get();
    }
}
