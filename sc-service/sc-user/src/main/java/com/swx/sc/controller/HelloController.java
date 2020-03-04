package com.swx.sc.controller;

import com.netflix.discovery.EurekaClient;
import com.netflix.discovery.shared.Application;
import javax.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author shiwenxiang
 * @date 2020/2/21
 */
@Slf4j
@RestController
public class HelloController {

    @Resource
    private EurekaClient eurekaClient;


    @GetMapping("/hello")
    public String hello() {
        Application application = eurekaClient.getApplication("sc-gateway");
        log.info("gateway port:{}", application.getInstances().get(0).getPort());

        return "hello";
    }

}
