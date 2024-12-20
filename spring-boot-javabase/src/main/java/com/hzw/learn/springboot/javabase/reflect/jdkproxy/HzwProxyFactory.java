package com.hzw.learn.springboot.javabase.reflect.jdkproxy;

import com.hzw.learn.springboot.javabase.reflect.proxyAndAOP.aop.Aop1;
import com.hzw.learn.springboot.javabase.reflect.proxyAndAOP.aop.Aop2;
import com.hzw.learn.springboot.javabase.reflect.proxyAndAOP.aop.AopInterface;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Proxy;

public class HzwProxyFactory {
	
	public static Object getProxy(Object target, InvocationHandler invocationHandler) {
		return Proxy.newProxyInstance(
				target.getClass().getClassLoader(), 
				target.getClass().getInterfaces(),
				invocationHandler);
	}
	
	// 使用泛型，避免了使用时强制类型转换
	//public static <T> T getProxy(T target) {
	//	HzwInvocationHandler invocationHandler = new HzwInvocationHandler(target, new AopInterface[] {new Aop1(), new Aop2()});
	//	return (T)getProxy(target, invocationHandler);
	//}
}
