package me.wubc.ribbon.controller;

import me.wubc.ribbon.entity.ResponseResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

/**
 * @author wbc
 * @date 2020/05/15
 * @desc
 **/
@RestController
@RequestMapping("/user")
public class UserRibbonControllerTest {

    @Autowired
    private RestTemplate restTemplate;

    @Value("${service-url.ribbon-provider-service}")
    private String userServiceUrl;


    @GetMapping("/getByUsername")
    public ResponseResult getByUsername(@RequestParam String username) {
        return restTemplate.getForObject(userServiceUrl + "/user/getByUsername?username={1}",
                ResponseResult.class, username);
    }

//    @GetMapping("/testGateWay")
//    public void testGateWay() {
//        for (int i = 0; i < 10; i++) {
//            restTemplate.getForObject("http://localhost:8072/user/getByUsername?username=1", String.class);
//        }
//    }

}
