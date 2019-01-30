package com.example.demo.config;

import org.springframework.context.EnvironmentAware;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

/**
 * Created by wujianjiang on 2019-1-9.
 */
@Component
public class PropertyConfigurer implements EnvironmentAware {
	
	private static Environment environment;

	/**
	 * 根据key回去value                         
	 * @param name
	 * @return
	 */
	public static String getProperty(String name) {
		return environment.getProperty(name);
	}

	@Override
	public void setEnvironment(Environment environment) {
		this.environment = environment;
	}
}
