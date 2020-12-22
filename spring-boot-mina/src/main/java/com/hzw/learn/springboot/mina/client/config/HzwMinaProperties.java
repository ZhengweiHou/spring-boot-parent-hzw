package com.hzw.learn.springboot.mina.client.config;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "hzwmina")
public class HzwMinaProperties {
	// 连接主机
	private String hostName = "localhost";
	// 连接端口
	private int port = 8080;
	// 链接超时时间
	private Long connectTimeoutInMillis = 30 * 1000l;
	// 闲置超时时间
	private int bothIdleTime = 30;

	private int maxActive = 50;
	
	private int maxIdle = 10;
	
	private int minIdle = 5;

	public void setHostName(String hostName) {
		this.hostName = hostName;
	}

	public void setPort(int port) {
		this.port = port;
	}

	public void setConnectTimeoutInMillis(Long connectTimeoutInMillis) {
		this.connectTimeoutInMillis = connectTimeoutInMillis;
	}

	public void setBothIdleTime(int bothIdleTime) {
		this.bothIdleTime = bothIdleTime;
	}

	public void setMaxActive(int maxActive) {
		this.maxActive = maxActive;
	}

	public void setMaxIdle(int maxIdle) {
		this.maxIdle = maxIdle;
	}

	public void setMinIdle(int minIdle) {
		this.minIdle = minIdle;
	}

	public String getHostName() {
		return hostName;
	}

	public int getPort() {
		return port;
	}

	public Long getConnectTimeoutInMillis() {
		return connectTimeoutInMillis;
	}

	public int getBothIdleTime() {
		return bothIdleTime;
	}

	public int getMaxActive() {
		return maxActive;
	}

	public int getMaxIdle() {
		return maxIdle;
	}

	public int getMinIdle() {
		return minIdle;
	}

}
