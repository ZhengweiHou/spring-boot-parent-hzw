package com.hzw.learn.nacos.boot.nacosconfig;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@Component
@ConfigurationProperties("hzw")
public class HzwProperties {
	private String hhh = "111";
	private String zzz = "222";
	private String www = "333";


	public String getHhh() {
		return hhh;
	}

	public void setHhh(String hhh) {
		this.hhh = hhh;
	}

	public String getZzz() {
		return zzz;
	}

	public void setZzz(String zzz) {
		this.zzz = zzz;
	}

	public String getWww() {
		return www;
	}

	public void setWww(String www) {
		this.www = www;
	}
}
