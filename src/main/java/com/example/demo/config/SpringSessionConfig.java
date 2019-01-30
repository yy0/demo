package com.example.demo.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.session.web.http.CookieHttpSessionIdResolver;
import org.springframework.session.web.http.CookieSerializer;
import org.springframework.session.web.http.DefaultCookieSerializer;
import org.springframework.session.web.http.HttpSessionIdResolver;

/**
 * Created by wujianjiang on 2019-1-9.
 */
@Configuration
public class SpringSessionConfig {

    @Value("${spring.session.cookie.name:SESSION}")
    private String cookieName;

    @Value("${spring.session.cookie.path:/}")
    private String cookiePath;

    @Bean
    public HttpSessionIdResolver cookieHttpSessionIdResolver() {
        CookieHttpSessionIdResolver sessionIdResolver = new CookieHttpSessionIdResolver();
        sessionIdResolver.setCookieSerializer(cookieSerializer());
        return sessionIdResolver;
    }

    @Bean
    public CookieSerializer cookieSerializer() {
        DefaultCookieSerializer cookieSerializer = new DefaultCookieSerializer();
        cookieSerializer.setCookieName(cookieName);
        cookieSerializer.setCookiePath(cookiePath);
        return cookieSerializer;
    }
}
