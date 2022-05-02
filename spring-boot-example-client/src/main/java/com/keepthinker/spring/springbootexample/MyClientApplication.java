package com.keepthinker.spring.springbootexample;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@EnableDiscoveryClient
@EnableFeignClients
@RestController
@SpringBootApplication
public class MyClientApplication {

    @RequestMapping("/")
    public String home() {
        return "Client: Hello World!";
    }

    public static void main(String[] args) {
        SpringApplication.run(MyClientApplication.class, args);
    }

}