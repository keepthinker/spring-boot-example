package com.keepthinker.spring.springbootexample.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;

@Component
public class ConfigCenterValueLoader extends PropertySourcesPlaceholderConfigurer implements BeanFactoryPostProcessor {
    private final Logger logger = LoggerFactory.getLogger(ConfigCenterValueLoader.class);

    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {
        super.setLocation(new ClassPathResource("config.properties"));
        super.postProcessBeanFactory(beanFactory);
    }

}
