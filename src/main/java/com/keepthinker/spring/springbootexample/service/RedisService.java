package com.keepthinker.spring.springbootexample.service;

import com.keepthinker.spring.springbootexample.entity.User;

import java.util.List;

public interface RedisService {

    /**
     * 保存属性
     */
    void setUsers(List<User> users);

    List<User> getUsers();
}