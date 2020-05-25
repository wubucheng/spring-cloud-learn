package me.wubc.consul.controller;


import lombok.extern.slf4j.Slf4j;
import me.wubc.consul.entity.BookPo;
import me.wubc.consul.entity.ResponseResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;


/**
 * @author wbc
 * @date 2020/5/25
 * @desc
 */
@Slf4j
@RestController
@RequestMapping("/book")
public class BookTestController {
    @Autowired
    private RestTemplate restTemplate;

    @Value("${service-url.book-service}")
    private String bookServiceUrl;

    @GetMapping("/getByName")
    public ResponseResult getByName(@RequestParam String name) {
        return restTemplate.getForObject(bookServiceUrl+"/book/getByName?name={1}",
                ResponseResult.class, name);
    }

}
