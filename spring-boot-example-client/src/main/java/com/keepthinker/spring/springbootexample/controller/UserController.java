package com.keepthinker.spring.springbootexample.controller;

import com.keepthinker.spring.springbootexample.dubbo.UserFacade;
import com.keepthinker.spring.springbootexample.dto.User;
import com.keepthinker.spring.springbootexample.feign.FeignUserFacade;
import com.keepthinker.spring.springbootexample.service.ExampleServerService;
import com.keepthinker.spring.springbootexample.utils.JsonUtils;
import org.apache.dubbo.config.annotation.DubboReference;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(produces = "application/json;charset=utf-8")
public class UserController {
    private Logger logger = LoggerFactory.getLogger(UserController.class);

    @DubboReference(group = "dev", version = "1.0")
    private UserFacade userService;

    @Autowired
    private FeignUserFacade feignUserFacade;

    @Autowired
    private ExampleServerService exampleServerService;


    @RequestMapping("/users")
    public List<User> getAllUsers(@RequestParam("page") int page, @RequestParam("size") int size, @RequestParam(name = "routingKey", defaultValue = "dubbo") String routingKey){
        List<User> users = null;
        if ("dubbo".equals(routingKey)) {
            users = userService.getUsers(page, size);
        } else if("nacos".equals(routingKey)){
            users = exampleServerService.getUsers(page, size);
        } else if ("feign".equals(routingKey)) {
            users = feignUserFacade.getUsers(page, size);
        }
        logger.info("result from dubbo server:{}", JsonUtils.objectToString(users));
        return users;
    }

}
