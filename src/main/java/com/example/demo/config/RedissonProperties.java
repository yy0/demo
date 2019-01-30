package com.example.demo.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * Created by wujianjiang on 2019-1-9.
 */
@Configuration
@ConfigurationProperties(prefix = "spring.redisson")
@Data
public class RedissonProperties {

    private String address = "127.0.0.1:6379";

    private int connectionMinimumIdleSize = 10;

    private int idleConnectionTimeout = 10000;

    private int pingTimeout = 1000;

    private int connectTimeout = 10000;

    private int timeout = 3000;

    private int retryAttempts = 3;

    private int retryInterval = 1500;

    private int reconnectionTimeout = 3000;

    private int failedAttempts = 3;

    private String password = null;

    private int subscriptionsPerConnection = 5;

    private String clientName = null;

    private int subscriptionConnectionMinimumIdleSize = 1;

    private int subscriptionConnectionPoolSize = 50;

    private int connectionPoolSize = 64;

    private int database = 0;

    private boolean dnsMonitoring = false;

    private int dnsMonitoringInterval = 5000;

    private int thread; //当前处理核数量 * 2

//    private String codec = "org.redisson.codec.JsonJacksonCodec";
}
