package com.hzw.learn.springboot.dubbo.hello.provider;

import org.apache.dubbo.config.annotation.Service;

@Service(version = "2.0.0")
public class HiImpl_Annotation implements Hi {
	
	private String name2="HiImpl";
	
	public HiImpl_Annotation() {}
	
	public HiImpl_Annotation(String name) {
		this.name2 = name;
	}

	@Override
	public String sayhi(String name) {
		System.out.println("recive for " + name);
		return "hi," + name + ",I'm " + name2;
		
	}

	@Override
	public String async_sayhi(String name) {
		// TODO Auto-generated method stub
		return null;
	}

}
