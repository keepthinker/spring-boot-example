package com.keepthinker.spring.springbootexample.feign;

import com.keepthinker.spring.springbootexample.dto.User;
import com.keepthinker.spring.springbootexample.loadbalance.LoadBalancerConfiguration;
import org.springframework.cloud.loadbalancer.annotation.LoadBalancerClient;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

//@LoadBalancerClient(name = "SPRING-BOOT-EXAMPLE-SERVER",
//        configuration= LoadBalancerConfiguration.class)
//@FeignClient(name = "SPRING-BOOT-EXAMPLE-SERVER")
//@LoadBalancerClient(name = "spring-boot-example-server",
//        configuration= LoadBalancerConfiguration.class)
@FeignClient(name = "spring-boot-example-server")
public interface FeignUserFacade {
   @RequestMapping(value = "/users", method = RequestMethod.GET, consumes = "application/json;charset=utf-8")
   List<User> getUsers(@RequestParam("page") int page, @RequestParam("size") int size);
}