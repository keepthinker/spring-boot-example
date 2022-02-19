package com.keepthinker.spring.springbootexample.dubbo.impl;

import com.keepthinker.spring.springbootexample.dubbo.UserFacade;
import com.keepthinker.spring.springbootexample.entity.User;
import com.keepthinker.spring.springbootexample.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserFacadeImpl implements UserFacade {

    @Autowired
    private UserService userService;
    @Override
    public List<User> getAllUsers() {
        return userService.getAllUsers();
    }
}
