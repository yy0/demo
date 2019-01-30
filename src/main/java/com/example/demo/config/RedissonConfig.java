package com.example.demo.config;

import io.netty.channel.nio.NioEventLoopGroup;
import org.redisson.Redisson;
import org.redisson.api.RedissonClient;
import org.redisson.codec.JsonJacksonCodec;
import org.redisson.config.Config;
import org.redisson.spring.cache.CacheConfig;
import org.redisson.spring.cache.RedissonSpringCacheManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by wujianjiang on 2019-1-9.
 */
@Configuration
public class RedissonConfig {

    @Autowired
    private RedissonProperties redissonProperties;

    @Bean(name = "redissonClient", destroyMethod = "shutdown")
    public RedissonClient redissonClient() throws Exception {
        Config config = new Config();
        config.useSingleServer().setAddress(redissonProperties.getAddress())
                .setConnectionMinimumIdleSize(redissonProperties.getConnectionMinimumIdleSize())
                .setConnectionPoolSize(redissonProperties.getConnectionPoolSize())
                .setDatabase(redissonProperties.getDatabase())
                .setDnsMonitoring(redissonProperties.isDnsMonitoring())
                .setDnsMonitoringInterval(redissonProperties.getDnsMonitoringInterval())
                .setSubscriptionConnectionMinimumIdleSize(redissonProperties.getSubscriptionConnectionMinimumIdleSize())
                .setSubscriptionConnectionPoolSize(redissonProperties.getSubscriptionConnectionPoolSize())
                .setSubscriptionsPerConnection(redissonProperties.getSubscriptionsPerConnection())
                .setClientName(redissonProperties.getClientName())
                .setFailedAttempts(redissonProperties.getFailedAttempts())
                .setRetryAttempts(redissonProperties.getRetryAttempts())
                .setRetryInterval(redissonProperties.getRetryInterval())
                .setReconnectionTimeout(redissonProperties.getReconnectionTimeout())
                .setTimeout(redissonProperties.getTimeout())
                .setConnectTimeout(redissonProperties.getConnectTimeout())
                .setIdleConnectionTimeout(redissonProperties.getIdleConnectionTimeout())
                .setPingTimeout(redissonProperties.getPingTimeout())
                .setPassword(redissonProperties.getPassword());
//        Codec codec=(Codec) ClassUtils.forName(redissonProperties.getCodec(), ClassUtils.getDefaultClassLoader()).newInstance();
//        config.setCodec(codec);
        config.setThreads(redissonProperties.getThread());
        config.setEventLoopGroup(new NioEventLoopGroup());
        config.setUseLinuxNativeEpoll(false);
        return Redisson.create(config);
    }

    public RedissonSpringCacheManager cacheManager() throws Exception {
        Map<String, CacheConfig> config = new HashMap<>();
        CacheConfig cacheConfig = new CacheConfig();
        cacheConfig.setMaxIdleTime(300000);
        cacheConfig.setTTL(1800000);
        config.put("collection", cacheConfig);
        RedissonSpringCacheManager cacheManager = new RedissonSpringCacheManager(redissonClient(), config, new JsonJacksonCodec());
        return cacheManager;
    }
}
