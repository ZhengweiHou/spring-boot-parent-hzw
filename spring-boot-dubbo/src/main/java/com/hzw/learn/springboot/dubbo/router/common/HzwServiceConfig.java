package com.hzw.learn.springboot.dubbo.router.common;

import org.apache.dubbo.config.ServiceConfig;

public class HzwServiceConfig<T> extends ServiceConfig<T>{

	public String queuename;

	public String getQueuename() {
		return queuename;
	}

	public void setQueuename(String queuename) {
		this.queuename = queuename;
	}
	
}
