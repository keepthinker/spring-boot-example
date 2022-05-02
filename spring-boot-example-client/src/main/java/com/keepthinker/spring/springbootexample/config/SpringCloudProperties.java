package com.keepthinker.spring.springbootexample.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "spring-cloud")
public class SpringCloudProperties {
    /**
     * Location of MyBatis xml config file.
     */
    private String loadBalanceType;

    public String getLoadBalanceType() {
        return loadBalanceType;
    }

    public void setLoadBalanceType(String loadBalanceType) {
        this.loadBalanceType = loadBalanceType;
    }
}