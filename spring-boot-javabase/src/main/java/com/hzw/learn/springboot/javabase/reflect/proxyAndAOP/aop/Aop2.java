package com.hzw.learn.springboot.javabase.reflect.proxyAndAOP.aop;

public class Aop2 implements AopInterface {

	@Override
	public void before() {
		System.out.println("aop2 before...");
	}

	@Override
	public void after() {
		System.out.println("aop2 after...");
	}

}
