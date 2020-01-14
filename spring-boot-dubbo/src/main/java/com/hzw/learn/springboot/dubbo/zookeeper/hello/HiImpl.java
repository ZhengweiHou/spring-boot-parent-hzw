package com.hzw.learn.springboot.dubbo.zookeeper.hello;

public class HiImpl implements Hi {
	
	private String name="HiImpl";
	
	public HiImpl() {}
	
	public HiImpl(String name) {
		this.name = name;
	}

	@Override
	public String sayhi(String name) {
		
		return "hi," + name + ",I'm " + name;
		
	}

}
