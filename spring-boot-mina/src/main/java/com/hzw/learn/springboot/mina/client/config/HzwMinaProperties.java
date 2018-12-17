package com.hzw.learn.springboot.mina.client.config;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "hzwmina")
public class HzwMinaProperties {
	private String hostName = "localhost";
	private int port = 8080;
	private Long connectTimeoutInMillis;
}
