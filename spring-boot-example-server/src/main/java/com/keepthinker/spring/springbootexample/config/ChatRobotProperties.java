package com.keepthinker.spring.springbootexample.config;

import com.alibaba.nacos.api.config.annotation.NacosValue;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "chatrobot")
public class ChatRobotProperties {
    /**
     * Location of MyBatis xml config file.
     */
    private String type;

    /**
     * process() return with "chat robot:" prefix
     */
    private boolean usePrefix;

    @NacosValue(value = "${chatRobotName:chat-robot}", autoRefreshed = true)
    private String name;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public boolean isUsePrefix() {
        return usePrefix;
    }

    public void setUsePrefix(boolean usePrefix) {
        this.usePrefix = usePrefix;
    }

    public String getName() {
        return name;
    }
}
