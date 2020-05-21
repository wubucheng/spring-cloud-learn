package me.wubc.hystrix.service;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCollapser;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import com.netflix.hystrix.contrib.javanica.cache.annotation.CacheResult;
import com.netflix.hystrix.contrib.javanica.command.AsyncResult;
import lombok.extern.slf4j.Slf4j;
import me.wubc.hystrix.entity.ResponseResult;
import me.wubc.hystrix.entity.UserPo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Map;
import java.util.concurrent.Future;

/**
 * @author wbc
 * @date 2020/05/18
 * @desc
 **/
@Slf4j
@Service
public class UserService {

    @Autowired
    private RestTemplate restTemplate;
    @Value("${service-url.ribbon-provider-service}")
    private String userServiceUrl;


    @HystrixCommand(fallbackMethod = "doFallBack")
    public ResponseResult getByUsername(String name) {
        return restTemplate.getForObject(userServiceUrl + "/user/getByUsername?username={1}", ResponseResult.class, name);
    }

    /**
     * 参数必须和controller的参数一致
     *
     * @param username
     * @return
     */
    public ResponseResult doFallBack(@RequestParam String username) {
        return new ResponseResult("调用失败", 500);
    }

    @CacheResult(cacheKeyMethod = "getCacheKey")
    public ResponseResult testCache(@RequestParam String name) {
        log.info("test cache" + name);
        return restTemplate.getForObject(userServiceUrl + "/user/getByUsername?username={1}", ResponseResult.class, name);
    }

    public String getCacheKey(String name) {
        log.info("getCacheKey" + name);
        return name;
    }

    @HystrixCollapser(batchMethod = "listUserByIds", collapserProperties = {
            @HystrixProperty(name = "timerDelayInMilliseconds", value = "100")
    })
    public Future<UserPo> getUser(String name) {
        return new AsyncResult<UserPo>() {
            @Override
            public UserPo invoke() {
                ResponseResult result = restTemplate.getForObject(userServiceUrl + "/user/getByUsername?username={1}", ResponseResult.class, name);
                log.info("result: " + result.toString());
                Map data = (Map) result.getData();
                UserPo userPo = new UserPo(data.get("name").toString(), Integer.parseInt(data.get("age").toString()));
                log.info("getUserById username:{}", userPo.getName());
                return userPo;
            }
        };

    }

    @HystrixCommand
    public List<UserPo> listUserByIds(List<String> names) {
        log.info("names: " + names);
        String[] nameArray = (String[]) names.stream().map(name -> name.concat(","))
                .toArray();
        ResponseResult result = restTemplate.getForObject(userServiceUrl + "/user/getByUsername?username={1}", ResponseResult.class, nameArray);
        return (List<UserPo>) result.getData();
    }
}
