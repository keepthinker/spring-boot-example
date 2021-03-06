package com.keepthinker.spring.springbootexample.service.impl;

import com.keepthinker.spring.springbootexample.dto.User;
import com.keepthinker.spring.springbootexample.service.RedisService;
import com.keepthinker.spring.springbootexample.utils.JsonUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.TimeUnit;

@Service
public class RedisServiceImpl implements RedisService {
    public static final String KEY_USERS = "users";

    private String usersKey(int page, int size){
        return "users" + ":" + page + ":" + size;
    }

    @Autowired
    private RedisTemplate<String, String> redisTemplate;

    @Override
    public void setUsers(List<User> users) {
        String value = JsonUtils.objectToString(users);
        redisTemplate.opsForValue().set(KEY_USERS, value, 20, TimeUnit.SECONDS);
    }

    @Override
    public List<User> getUsers(int page, int size) {
        String value = redisTemplate.opsForValue().get(usersKey(page, size));
        if (value == null) {
            return null;
        }
        return JsonUtils.stringToList(value);
    }

}