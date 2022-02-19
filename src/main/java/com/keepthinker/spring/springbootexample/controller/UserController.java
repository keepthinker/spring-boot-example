package com.keepthinker.spring.springbootexample.controller;

import com.keepthinker.spring.springbootexample.entity.User;
import com.keepthinker.spring.springbootexample.mapper.UserMapper;
import com.keepthinker.spring.springbootexample.service.RedisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class UserController {

    @Autowired
    private RedisService redisService;

    @Autowired
    private UserMapper userMapper;
    @RequestMapping("/users")
    public List<User> getAllUsers(){
        List<User> users = redisService.getUsers();
        if (users == null) {
            users = userMapper.findAll();
            redisService.setUsers(users);
        }
        return users;
    }

}
