package com.hzw.learn.springboot.springbootbase.configtest;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "hzw")
public class HzwEnv {
	private Map<String, Object> hzwmaps = new LinkedHashMap<String, Object>();
	public List<String> hzwlist;
	public hzw hzw1;
	private List<hzw> hzw2s;
	private Map<String,hzw> hzwmaps2;

	public hzw getHzw1() {
		return hzw1;
	}

	public void setHzw1(hzw hzw1) {
		this.hzw1 = hzw1;
	}

	public List<String> getHzwlist() {
		return hzwlist;
	}

	public void setHzwlist(List<String> hzwlist) {
		this.hzwlist = hzwlist;
	}

	public Map<String, Object> getHzwmaps() {
		return hzwmaps;
	}

	public void setHzwmaps(Map<String, Object> hzwmaps) {
		this.hzwmaps = hzwmaps;
	}

	public List<hzw> getHzw2s() {
		return hzw2s;
	}

	public void setHzw2s(List<hzw> hzw2s) {
		this.hzw2s = hzw2s;
	}

	public Map<String, hzw> getHzwmaps2() {
		return hzwmaps2;
	}

	public void setHzwmaps2(Map<String, hzw> hzwmaps2) {
		this.hzwmaps2 = hzwmaps2;
	}
}

class hzw {
	public String a;
	public String b;

	public String getA() {
		return a;
	}

	public void setA(String a) {
		this.a = a;
	}

	public String getB() {
		return b;
	}

	public void setB(String b) {
		this.b = b;
	}
}