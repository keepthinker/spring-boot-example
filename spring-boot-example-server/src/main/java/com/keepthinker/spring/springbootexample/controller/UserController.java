package com.keepthinker.spring.springbootexample.controller;

import com.keepthinker.spring.springbootexample.dto.User;
import com.keepthinker.spring.springbootexample.service.ChatRobotService;
import com.keepthinker.spring.springbootexample.service.UserService;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class UserController {

    @Autowired
    private ChatRobotService chatRobotService;

    @Autowired
    private UserService userService;

    @RequestMapping("/users")
    public List<User> getAllUsers(@RequestParam("page") int page, @RequestParam("size") int size) {
        return userService.getUsers(page, size);
    }

    @RequestMapping("/talk")
    public String chatRootTalk(@Param("message") String message) {
        return chatRobotService.reply(message);
    }

    @RequestMapping(path = "/users/create-batch", method = RequestMethod.POST)
    public void createUsers(@RequestParam int size) {
        userService.createUsers(size);
    }

    @RequestMapping(path = "/users/update-batch", method = RequestMethod.POST)
    public void createUsers() {
        userService.updateUsers();
    }

    @RequestMapping(path = "/chat-robot/name", method = RequestMethod.GET)
    public String getChatRootName() {
        return chatRobotService.name();
    }
}
