package com.hzw.learn.springboot.dubbo.router.api;

public class RouterHiImpl implements RouterHiApi{

	@Override
	public String hi(String msg) {
		System.out.println("======");
		System.out.println("receive msg :" + msg 
				+ "   appname:" + System.getProperty("appname", "application") 
				+ "  appport:" + System.getProperty("appport"));
		return "appport:" + System.getProperty("appport") + "   " + getClass().getName();
	}

}
