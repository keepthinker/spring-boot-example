package com.keepthinker.spring.springbootexample.controller;

import com.keepthinker.spring.springbootexample.entity.User;
import com.keepthinker.spring.springbootexample.mapper.UserMapper;
import com.keepthinker.spring.springbootexample.service.RedisService;
import com.keepthinker.spring.springbootexample.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @RequestMapping("/users")
    public List<User> getAllUsers(){
        return userService.getAllUsers();
    }

}
