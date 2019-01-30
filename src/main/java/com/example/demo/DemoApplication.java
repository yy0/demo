package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.session.config.annotation.web.http.EnableSpringHttpSession;

@SpringBootApplication
@EnableAspectJAutoProxy(proxyTargetClass = true)
@ServletComponentScan
@EnableSpringHttpSession
public class DemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}

}

