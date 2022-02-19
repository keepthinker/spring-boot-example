package com.keepthinker.spring.springbootexample.service.impl;

import com.keepthinker.spring.springbootexample.config.ChatRobotProperties;
import com.keepthinker.spring.springbootexample.service.ChatRobotService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ChatRobot implements InitializingBean, ChatRobotService {
    private Logger logger = LoggerFactory.getLogger(ChatRobot.class);

    private ChatRobotProcess chatRobotService;

    @Autowired
    private ChatRobotProperties chatRobotProperties;

    public String reply(String input) {
        return chatRobotService.process(input);
    }

    @Override
    public void afterPropertiesSet() {
        logger.info("use chat robot service|type:{}", chatRobotProperties.getType());
        if (chatRobotProperties.getType().equals("adorable")) {
            chatRobotService = new AdorableChatRobot();
        } else if (chatRobotProperties.getType().equals("unfriendly")) {
            chatRobotService = new UnfriendlyChatRobot();
        } else {
            chatRobotService = new AdorableChatRobot();
        }
    }

    private static class AdorableChatRobot implements ChatRobotProcess {

        @Override
        public String process(String input) {
            input = input.toLowerCase();
            if (input.contains("help")) {
                return "No problem!";
            } else if (input.contains("may")) {
                return "OK!";
            } else if (input.contains("thank")) {
                return "My Pleasure!";
            } else {
                return "Yeah!";
            }
        }
    }

    private static class UnfriendlyChatRobot implements ChatRobotProcess {

        @Override
        public String process(String input) {
            input = input.toLowerCase();
            if (input.contains("help")) {
                return "Sorry!";
            } else if (input.contains("may")) {
                return "I am busy!";
            } else if (input.contains("thank")) {
                return "Oh!";
            } else {
                return "Oh!";
            }
        }
    }
}
