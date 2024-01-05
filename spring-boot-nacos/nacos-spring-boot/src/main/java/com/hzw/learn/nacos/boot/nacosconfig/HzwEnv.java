package com.hzw.learn.nacos.boot.nacosconfig;

import com.alibaba.nacos.api.config.annotation.NacosConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@Component
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
