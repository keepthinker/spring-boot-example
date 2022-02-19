package com.keepthinker.spring.springbootexample.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.context.event.ApplicationEnvironmentPreparedEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.core.env.PropertiesPropertySource;

import java.util.Properties;

public class ConfigCenterHandler implements ApplicationListener<ApplicationEnvironmentPreparedEvent> {
  private final Logger logger = LoggerFactory.getLogger(ConfigCenterHandler.class);
  private static final String CHAT_ROBOT_TYPE = "chatrobot.type";
  private static final String CHAT_ROBOT_DEFAULT_TYPE = "adorable";

  public void onApplicationEvent(ApplicationEnvironmentPreparedEvent event) {
    ConfigurableEnvironment environment = event.getEnvironment();
    if (environment.getProperty(CHAT_ROBOT_TYPE) == null){
      logger.info("{} is not configured, use default value: {}", CHAT_ROBOT_TYPE, CHAT_ROBOT_DEFAULT_TYPE);
      Properties props = new Properties();
      props.put(CHAT_ROBOT_TYPE, CHAT_ROBOT_DEFAULT_TYPE);
      environment.getPropertySources().addFirst(new PropertiesPropertySource("chatRobotProperties", props));
    }
  }

}