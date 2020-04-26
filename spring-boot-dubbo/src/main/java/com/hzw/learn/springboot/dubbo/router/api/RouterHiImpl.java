package com.hzw.learn.springboot.dubbo.router.api;

public class RouterHiImpl implements RouterHiApi{

	@Override
	public String hi(String msg) {
		System.out.println(System.getProperty("appname", "application")
				+":" + System.getProperty("appport")
				+ " received msg :" + msg);
		return "appport:" + System.getProperty("appport") + "   " + getClass().getName();
	}

}
