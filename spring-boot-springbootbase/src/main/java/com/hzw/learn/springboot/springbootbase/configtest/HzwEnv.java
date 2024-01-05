package com.hzw.learn.springboot.springbootbase.configtest;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "hzwenv")
public class HzwEnv {
	private Map<String, Object> hzwmaps = new LinkedHashMap<String, Object>();

	public Map<String, Object> getHzwmaps() {
		return hzwmaps;
	}

	public void setHzwmaps(Map<String, Object> hzwmaps) {
		this.hzwmaps = hzwmaps;
	}



	public List<String> hzwlist;

	public void setHzwlist(List<String> hzwlist) {
		this.hzwlist = hzwlist;
	}
}
