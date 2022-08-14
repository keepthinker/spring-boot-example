package com.keepthinker.spring.springbootexample.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DiscoveryController {

    @Autowired
    private DiscoveryClient discoveryConsumer;

    @RequestMapping("/discovery/service_instances")
    public Object getServices(){
        return discoveryConsumer.getServices();
    }

    @RequestMapping("/discovery/service_instances/{serviceId}")
    public Object getInstance(@PathVariable("serviceId") String serviceId){
        return discoveryConsumer.getInstances(serviceId);
    }

}
