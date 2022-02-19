package com.keepthinker.spring.springbootexample.controller;

import com.keepthinker.spring.springbootexample.dto.User;
import com.keepthinker.spring.springbootexample.service.ChatRobotService;
import com.keepthinker.spring.springbootexample.service.UserService;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class UserController {

    @Autowired
    private ChatRobotService chatRobotService;

    @Autowired
    private UserService userService;

    @RequestMapping("/users")
    public List<User> getAllUsers(){
        return userService.getAllUsers();
    }

    @RequestMapping("/talk")
    public String chatRootTalk(@Param("message") String message){
        return chatRobotService.reply(message);
    }

}
