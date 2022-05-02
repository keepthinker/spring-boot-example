package com.keepthinker.spring.springbootexample.loadbalance;

import com.keepthinker.spring.springbootexample.config.SpringCloudProperties;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.loadbalancer.core.RandomLoadBalancer;
import org.springframework.cloud.loadbalancer.core.ReactorLoadBalancer;
import org.springframework.cloud.loadbalancer.core.RoundRobinLoadBalancer;
import org.springframework.cloud.loadbalancer.core.ServiceInstanceListSupplier;
import org.springframework.cloud.loadbalancer.support.LoadBalancerClientFactory;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;

@Configuration
public class LoadBalancerConfiguration {
    @Autowired
    private SpringCloudProperties springCloudProperties;
    private static Logger logger = LoggerFactory.getLogger(LoadBalancerConfiguration.class);

    @Bean
    public ServiceInstanceListSupplier discoveryClientServiceInstanceListSupplier(
            ConfigurableApplicationContext context) {
        logger.info("Configuring Load balancer"); // to prefer same instance
        return ServiceInstanceListSupplier.builder()
                .withBlockingDiscoveryClient()
//                .withSameInstancePreference()
                .build(context);
    }

    @Bean
    public ReactorLoadBalancer<ServiceInstance> reactorServiceInstanceLoadBalancer(Environment environment,
                                                                                   LoadBalancerClientFactory loadBalancerClientFactory) {
        String name = environment.getProperty(LoadBalancerClientFactory.PROPERTY_NAME);
//        if (StringUtils.isBlank(name)) {
//            name = "load-balancer-lazy-provider";
//        }
        if (springCloudProperties.getLoadBalanceType().equals("random")) {
            return new RandomLoadBalancer(
                    loadBalancerClientFactory.getLazyProvider(name, ServiceInstanceListSupplier.class), name);
        } else {
            return new RoundRobinLoadBalancer(
                    loadBalancerClientFactory.getLazyProvider(name, ServiceInstanceListSupplier.class), name);

        }
    }
}