package com.keepthinker.spring.springbootexample.dubbo.impl;

import com.keepthinker.spring.springbootexample.dto.User;
import com.keepthinker.spring.springbootexample.dubbo.UserFacade;
import com.keepthinker.spring.springbootexample.service.UserService;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@DubboService
public class UserFacadeImpl implements UserFacade {

    @Autowired
    private UserService userService;
    @Override
    public List<User> getAllUsers() {
        return userService.getAllUsers();
    }
}
