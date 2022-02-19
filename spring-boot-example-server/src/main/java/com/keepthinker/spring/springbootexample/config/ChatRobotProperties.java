package com.keepthinker.spring.springbootexample.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "chatrobot")
public class ChatRobotProperties {
    /**
     * Location of MyBatis xml config file.
     */
    private String type;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
