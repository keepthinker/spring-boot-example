package com.keepthinker.spring.springbootexample.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.context.event.ApplicationEnvironmentPreparedEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.core.env.PropertiesPropertySource;

import java.util.Properties;

/**
 * 在classpath:META-INF/spring.properties中绑定到SpringBoot，仿照java中的SPI扩展机制实现的。
 * SpringBoot在创建ApplicationContext之前，会先调用prepareEnvironment方法准备创建容器所需要的环境信息，即创建Environment，并加载配置到Environment。
 */
public class ConfigCenterDefaultValueHandler implements ApplicationListener<ApplicationEnvironmentPreparedEvent> {
  private final Logger logger = LoggerFactory.getLogger(ConfigCenterDefaultValueHandler.class);
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