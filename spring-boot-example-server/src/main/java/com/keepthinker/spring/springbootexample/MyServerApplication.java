package com.keepthinker.spring.springbootexample;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@EnableDiscoveryClient
@RestController
@SpringBootApplication
public class MyServerApplication extends SpringBootServletInitializer {

    @RequestMapping("/")
    public String home() {
        return "Server: Hello World!";
    }

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(MyServerApplication.class);
    }

    public static void main(String[] args) {
        SpringApplication.run(MyServerApplication.class, args);
    }

}