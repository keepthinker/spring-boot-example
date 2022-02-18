package com.keepthinker.spring.springbootexample.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;

@Configuration
//@EnableTransactionManagement
@MapperScan({"com.keepthinker.spring.springbootexample.dao"})
public class MyBatisConfig {
}