package com.hzw.learn.springboot.dubbo.router.common;

import org.apache.dubbo.config.ReferenceConfig;

public class HzwReferenceConfig<T> extends ReferenceConfig<T>{
	public String queuename;

	public String getQueuename() {
		return queuename;
	}

	public void setQueuename(String queuename) {
		this.queuename = queuename;
	}
}
