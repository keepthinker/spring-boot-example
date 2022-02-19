package com.keepthinker.spring.springbootexample.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@MapperScan({"com.keepthinker.spring.springbootexample.mapper"})
public class MyBatisConfig {
}