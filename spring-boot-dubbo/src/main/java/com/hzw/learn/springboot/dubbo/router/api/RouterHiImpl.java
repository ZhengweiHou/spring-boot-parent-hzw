package com.hzw.learn.springboot.dubbo.router.api;

public class RouterHiImpl implements RouterHiApi{

	@Override
	public String hi(String msg) {
		System.out.println("receive msg :" + msg);
		return getClass().getName();
	}

}
