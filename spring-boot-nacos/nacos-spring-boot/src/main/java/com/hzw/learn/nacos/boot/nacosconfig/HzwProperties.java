package com.hzw.learn.nacos.boot.nacosconfig;

import com.alibaba.nacos.api.annotation.NacosProperties;
import com.alibaba.nacos.api.config.ConfigType;
import com.alibaba.nacos.api.config.annotation.NacosValue;
import com.alibaba.nacos.spring.context.annotation.config.NacosPropertySource;
import com.alibaba.nacos.spring.context.annotation.config.NacosPropertySources;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@ConfigurationProperties(prefix = "hzw")
//@NacosPropertySource(dataId = "test",groupId = "test",autoRefreshed = true,type = ConfigType.YAML, prefix = "hzw")
public class HzwProperties {
//	@NacosValue(value = "${hzw.hhh:111}", autoRefreshed = true)
	private String hhh;
	
//	@NacosValue(value = "${hzw.zzz:222}", autoRefreshed = true)
	private String zzz;
	
//	@NacosValue(value = "${hzw.www:333}", autoRefreshed = true)
	private String www;
	
	// @NacosValue(value = "${hzw.hzws}", autoRefreshed = true)
	private List<String> hzws;
	private List<hzw> hzw2s;


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

	public List<hzw> getHzw2s() {
		return hzw2s;
	}

	public void setHzw2s(List<hzw> hzw2s) {
		this.hzw2s = hzw2s;
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
