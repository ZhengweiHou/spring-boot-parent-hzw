package com.hzw.learn.springboot.dubbo.hello.provider;

public class HiImpl implements Hi {
	
	private String name2="HiImpl";
	
	public HiImpl() {}
	
	public HiImpl(String name) {
		this.name2 = name;
	}

	@Override
	public String sayhi(String name) {
		System.out.println(name2 + "recive for " + name);
		return "hi," + name + ",I'm " + name2;
		
	}

	@Override
	public String async_sayhi(String name) {
		// TODO Auto-generated method stub
		return null;
	}

}
