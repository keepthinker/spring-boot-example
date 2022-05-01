package com.keepthinker.spring.springbootexample.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class EurekaController {

    @Autowired
    private DiscoveryClient eurekaConsumer;

    @RequestMapping("/eureka/service_instances")
    public Object getServices(){
        return eurekaConsumer.getServices();
    }

    // serviceId is "Application" of "Instances currently registered with Eureka" in eureka dashboard
    @RequestMapping("/eureka/service_instances/{serviceId}")
    public Object getInstance(@PathVariable("serviceId") String serviceId){
        return eurekaConsumer.getInstances(serviceId);
    }

}
