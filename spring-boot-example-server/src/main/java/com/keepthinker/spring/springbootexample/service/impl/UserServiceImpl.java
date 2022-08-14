package com.keepthinker.spring.springbootexample.service.impl;

import com.keepthinker.spring.springbootexample.dto.User;
import com.keepthinker.spring.springbootexample.mapper.UserMapper;
import com.keepthinker.spring.springbootexample.service.RedisService;
import com.keepthinker.spring.springbootexample.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private RedisService redisService;
    @Autowired
    private UserMapper userMapper;

    public List<User> getUsers(int page, int size) {
        List<User> users = redisService.getUsers(page, size);
        if (users == null) {
            users = userMapper.getUsers((page - 1) * size, size);
            redisService.setUsers(users);
        }
        return users;
    }

    @Transactional
    @Override
    public void createUsers(int size) {
        ThreadLocalRandom random = ThreadLocalRandom.current();
        if (size < 100) {
            for (int i = 0; i < size; i++) {
                User user = new User();
                user.setName("name" + random.nextInt(size));
                user.setAge(random.nextInt(100));
                userMapper.insert(user);
            }
        } else {
            List<User> users = new ArrayList<>();
            for (int i = 0; i < size; i++) {
                User user = new User();
                user.setName("name" + random.nextInt(size));
                user.setAge(random.nextInt(100));
                users.add(user);
                if (users.size() == 100) {
                    userMapper.insertBatch(users);
                    users.clear();
                }
            }
            if (users.size() > 0) {
                userMapper.insertBatch(users);
            }
        }

    }

    @Transactional
    @Override
    public void updateUsers() {
        int size = userMapper.count();
        ThreadLocalRandom random = ThreadLocalRandom.current();
        for (int i = 0; i < size; i++) {
            User user = new User();
            user.setId(i);
            user.setName("name" + random.nextInt(size));
            user.setAge(random.nextInt(100));
            userMapper.update(user);
        }

    }
}
