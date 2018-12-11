package com.hzw.learn.springboot.javabase.reflect.proxyAndAOP;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.util.Arrays;

import com.hzw.learn.springboot.javabase.reflect.proxyAndAOP.aop.AopInterface;

public class HzwInvocationHandler implements InvocationHandler{
	
	private AopInterface[] aops;
	private Object target;
	
	public HzwInvocationHandler(Object target, AopInterface[] aops) {
		this.target = target;
		this.aops = aops;
	}
	
	@Override
	public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
		Method beforeMethod = AopInterface.class.getMethod("before");
		Method afterMethod = AopInterface.class.getMethod("after");
		
		Arrays.asList(aops).stream().forEach(aop -> {
			try {
				beforeMethod.invoke(aop);
			} catch (Exception e) {e.printStackTrace();}
		});
		method.setAccessible(true);
		Object result = method.invoke(target, args);
		
		Arrays.asList(aops).stream().forEach(aop -> {
			try {
				afterMethod.invoke(aop);
			} catch (Exception e) {e.printStackTrace();}
		});
		
		return result;
	}

}
