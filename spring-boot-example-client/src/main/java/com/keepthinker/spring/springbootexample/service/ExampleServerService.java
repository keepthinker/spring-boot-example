package com.keepthinker.spring.springbootexample.service;

import com.keepthinker.spring.springbootexample.dto.User;
import com.keepthinker.spring.springbootexample.utils.JsonUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
public class ExampleServerService {

    private final RestTemplate restTemplate;

    @Autowired
    public ExampleServerService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public List<User> getUsers(int page, int size) {
        String response = restTemplate.getForObject("http://spring-boot-example-server/users?page=" + page + "&size=" + size, String.class);
        return JsonUtils.stringToList(response);
    }
}