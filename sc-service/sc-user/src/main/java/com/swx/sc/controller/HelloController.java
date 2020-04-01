package com.swx.sc.controller;

import com.netflix.discovery.EurekaClient;
import com.swx.sc.bean.entity.User;
import com.swx.sc.service.UserService;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author shiwenxiang
 * @date 2020/2/21
 */
@Slf4j
@RestController
@RequestMapping("/users")
public class HelloController {

    @Resource
    private EurekaClient eurekaClient;
    @Autowired
    private UserService userService;

    @GetMapping("/list")
    public Object hello(HttpServletRequest request) throws Exception {
        log.info("parameter colour:{}", request.getParameter("colour"));
        log.info("header X-Request-fruit:{}", request.getHeader("X-Request-Fruit"));
        return userService.list();
    }

    @GetMapping("/update/{id}")
    public Object update(@PathVariable("id") Long id) throws Exception {
        User user = User.builder()
                .id(id)
                .age(99)
                .build();
        userService.updateUser(user);
        return "update ok";
    }


}
