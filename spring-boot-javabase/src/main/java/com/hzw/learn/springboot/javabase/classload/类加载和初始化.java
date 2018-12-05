package com.hzw.learn.springboot.javabase.classload;

import java.net.URL;

public class 类加载和初始化 {
	public static void main(String[] args) throws ClassNotFoundException {
		
		System.out.println("loader.loaderClass");
		// 加载类，但是并不初始化
		ClassLoader loader = ClassLoader.getSystemClassLoader();
		Class<?> aa = loader.loadClass("com.hzw.learn.springboot.javabase.classload.HZWTest_1");
		
		System.out.println("Class.forName");
		// 加载并初始化类
		Class<?> bb = Class.forName("com.hzw.learn.springboot.javabase.classload.HZWTest_1");
		
		// 获取类加载起所加载的全部URL数组
		URL[] urls = sun.misc.Launcher.getBootstrapClassPath().getURLs();
	}
}

/*
 执行结果：
loader.loaderClass
Class.forName
HZWTest_1类的静态初始化块
 */

class HZWTest_1 {
	static
	{
		System.out.println("HZWTest_1类的静态初始化块");
	}
}
