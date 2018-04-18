package com.hzw.learn.springboot.consul;

import java.util.HashMap;
import java.util.Map;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "env")
public class EnvValue {
	private Map<String, String> envmaps = new HashMap<String, String>();

	public Map<String, String> getEnvmaps() {
		return envmaps;
	}

	public void setEnvmaps(Map<String, String> envmaps) {
		this.envmaps = envmaps;
	}

}
