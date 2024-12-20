package com.hzw.learn.springboot.javabase.reflect.jdkproxy;

import com.hzw.learn.springboot.javabase.reflect.proxyAndAOP.aop.AopInterface;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.util.Arrays;

public class HzwInvocationHandler implements InvocationHandler{

	@Override
	public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {


		return "hello";
	}

}
