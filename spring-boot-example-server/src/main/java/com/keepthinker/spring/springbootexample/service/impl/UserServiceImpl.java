package com.keepthinker.spring.springbootexample.service.impl;

import com.keepthinker.spring.springbootexample.dto.User;
import com.keepthinker.spring.springbootexample.mapper.UserMapper;
import com.keepthinker.spring.springbootexample.service.RedisService;
import com.keepthinker.spring.springbootexample.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private RedisService redisService;
    @Autowired
    private UserMapper userMapper;

    public List<User> getAllUsers() {
    List<User> users = redisService.getUsers();
        if (users == null) {
        users = userMapper.findAll();
        redisService.setUsers(users);
    }
        return users;
    }
}
