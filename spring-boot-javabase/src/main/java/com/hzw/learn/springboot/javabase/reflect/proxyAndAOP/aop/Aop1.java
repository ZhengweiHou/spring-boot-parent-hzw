package com.hzw.learn.springboot.javabase.reflect.proxyAndAOP.aop;

public class Aop1 implements AopInterface {

	@Override
	public void before() {
		System.out.println("aop1 before...");
	}

	@Override
	public void after() {
		System.out.println("aop1 after...");
	}

}
