package com.hzw.learn.springboot.springbootbase.configtest;

import java.util.HashMap;
import java.util.Map;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "hzwenv")
public class HzwEnv {
	private Map<String, Object> hzwmaps = new HashMap<String, Object>();

	public Map<String, Object> getHzwmaps() {
		return hzwmaps;
	}

	public void setHzwmaps(Map<String, Object> hzwmaps) {
		this.hzwmaps = hzwmaps;
	}

}
