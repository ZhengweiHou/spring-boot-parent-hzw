package com.hzw.learn.springboot.javabase.reflect;

import com.hzw.learn.springboot.javabase.reflect.proxyAndAOP.HzwProxyFactory;

public class Proxy和Aop演示 {
	public static void main(String[] args) {
		Car car = HzwProxyFactory.getProxy(new F1());
		car.start();
		car.run();
	}
/*
aop1 before...
aop2 before...
F1赛车启动！
aop1 after...
aop2 after...
aop1 before...
aop2 before...
F1赛车加速！！
aop1 after...
aop2 after...
 */
}

interface Car {
	public void start();
	public void run();
}

class F1 implements Car{

	@Override
	public void start() {
		System.out.println("F1赛车启动！");
	}

	@Override
	public void run() {
		System.out.println("F1赛车加速！！");
	}
	
}