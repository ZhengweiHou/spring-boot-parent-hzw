package com.hzw.learn.springboot.javabase.reflect.proxy;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class ProxyTest {
	public static void main(String[] args) throws NoSuchMethodException, SecurityException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, InstantiationException {
		// 创建InvocationHandler对象
		InvocationHandler handler = new MyInvocationHandler();
		// 使用制定的InvocationHandler生成一个动态代理对象
		Class<?> proxyClass = Proxy.getProxyClass(Person.class.getClassLoader(), new Class[] {Person.class});
		System.out.println(proxyClass.getName());
		Constructor<?> ctor = proxyClass.getConstructor(InvocationHandler.class);
		Object temp = ctor.newInstance(handler);
		// 下行为简化写法
//		Object temp = Proxy.newProxyInstance(Person.class.getClassLoader(), new Class[]{Person.class} , handler);
		
		Method walkMethod = Person.class.getMethod("walk");
		walkMethod.invoke(temp);
		Method sayHelloMethod = Person.class.getMethod("sayHello", String.class);
		sayHelloMethod.invoke(temp,"HZW");
		
		Person p = (Person)temp;
		p.walk();
		p.sayHello("hzw");
	}
}

interface Person {
	void walk();
	void sayHello(String name);
}

class MyInvocationHandler implements InvocationHandler {
	/*
	 * 执行动态代理对象的所有方法时，都会被替换成执行如下的invoke方法 方法参数解释： proxy: 代表动态代理对象 method: 代表正在执行的方法
	 * args: 代表调用目标方法时传入的实参
	 */
	@Override
	public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
		System.out.println("动态代理对象名：" + proxy.getClass().getSimpleName());
		System.out.println("执行方法名：" + method.getName());
		System.out.println("执行方法参数：" + args);
		return null;
	}
}
