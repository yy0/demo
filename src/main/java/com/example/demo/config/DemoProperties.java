package com.example.demo.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

/**
 * Created by wujianjiang on 2019-2-25.
 */
@Component
@ConfigurationProperties
@PropertySource(value = "classpath:demo-${spring.profiles.active}.properties")
public class DemoProperties {
}
