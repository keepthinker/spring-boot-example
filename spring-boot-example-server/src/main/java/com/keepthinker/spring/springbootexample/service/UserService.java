package com.keepthinker.spring.springbootexample.service;

import com.keepthinker.spring.springbootexample.dto.User;

import java.util.List;

public interface UserService {
    List<User> getUsers(int page, int size);
    void createUsers(int size);
    void updateUsers();
}
