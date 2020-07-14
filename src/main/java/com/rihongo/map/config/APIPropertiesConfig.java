package com.rihongo.map.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

import java.util.Map;

@Configuration
@PropertySource("classpath:api.properties")
@ConfigurationProperties(prefix = "kakao.api")
public class APIPropertiesConfig {

    @Getter
    @Setter
    private String key;

    @Getter
    @Setter
    private Map<String, String> url;

}
