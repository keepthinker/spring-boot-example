package com.keepthinker.spring.springbootexample.config;

import com.alibaba.nacos.spring.context.annotation.config.NacosPropertySource;
import org.springframework.stereotype.Component;

@Component
@NacosPropertySource(dataId = "spring-boot-example", autoRefreshed = true)
public class NacosConfiguration {
}
