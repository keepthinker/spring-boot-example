package com.keepthinker.spring.springbootexample.dubbo;

import com.keepthinker.spring.springbootexample.entity.User;

import java.util.List;

public interface UserFacade {
    List<User> getAllUsers();
}
