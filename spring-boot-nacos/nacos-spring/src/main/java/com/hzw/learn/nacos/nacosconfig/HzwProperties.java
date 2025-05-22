package com.hzw.learn.nacos.nacosconfig;

import org.springframework.stereotype.Component;

import java.util.List;

@Component
//@ConfigurationProperties(prefix = "hzw") // ConfigurationProperties 是springboot提供的，仅spring项目无法使用
public class HzwProperties {
	private String hhh;
	
	private String zzz;
	
	private String www;
	
	private List<String> hzws;


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
	public List<String> getHzws() {
		return hzws;
	}
	public void setHzws(List<String> hzws) {
		this.hzws = hzws;
	}
}
