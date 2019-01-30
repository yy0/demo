package com.example.demo.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.listener.ChannelTopic;
import org.springframework.data.redis.listener.RedisMessageListenerContainer;
import org.springframework.data.redis.listener.adapter.MessageListenerAdapter;

/**
 * Created by wujianjiang on 2019-1-9.
 */
@Configuration
public class RedisMQConfig {

    @Value("${spring.redis.host}")
    private String host;

    @Value("${spring.redis.port}")
    private int port;

    @Value("${spring.redis.topic}")
    private String topic;

    @Bean
    public ScanMessageDelegate scanMessageDelegate() {
        ScanMessageDelegate delegate = new ScanMessageDelegate();
        return delegate;
    }

    @Bean
    public MessageListenerAdapter messageListenerAdapter(ScanMessageDelegate delegate) {
        return new MessageListenerAdapter(delegate, "handleMessage");
    }

    @Bean
    public RedisMessageListenerContainer scanRedisMessageListenerContainer() {
        RedisMessageListenerContainer listenerContainer = new RedisMessageListenerContainer();
        RedisStandaloneConfiguration standaloneConfig = new RedisStandaloneConfiguration(host, port);
        JedisConnectionFactory jedisConnectionFactory = new JedisConnectionFactory(standaloneConfig);
        listenerContainer.setConnectionFactory(jedisConnectionFactory);
        ScanMessageDelegate delegate = scanMessageDelegate();
        MessageListenerAdapter messageListener = messageListenerAdapter(delegate);
        listenerContainer.addMessageListener(messageListener, new ChannelTopic(topic));
        return listenerContainer;
    }

    class ScanMessageDelegate {

        public void handleMessage(String message) {
            System.out.println(message);
        }

    }
}


