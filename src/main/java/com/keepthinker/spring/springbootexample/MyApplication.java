package com.keepthinker.spring.springbootexample;

import com.keepthinker.spring.springbootexample.dao.UserDao;
import com.keepthinker.spring.springbootexample.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@SpringBootApplication
public class MyApplication {

    @Autowired
    private UserDao userDao;

    @RequestMapping("/")
    String home() {
        return "Hello World!";
    }

    @RequestMapping("/users")
    public List<User> getAllUsers(){
        return userDao.findAll();
    }

    public static void main(String[] args) {
        SpringApplication.run(MyApplication.class, args);
    }

}