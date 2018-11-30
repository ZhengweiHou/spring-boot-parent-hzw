package com.hzw.learn.springboot.javabase.classload;

public class ClassLoaderTest {
	public static void main(String[] args) throws ClassNotFoundException {
		
		System.out.println("loader.loaderClass");
		// 加载类，但是并不初始化
		ClassLoader loader = ClassLoader.getSystemClassLoader();
		loader.loadClass("com.hzw.learn.springboot.javabase.classload.HZWTest_1");
		
		System.out.println("Class.forName");
		// 加载并初始化类
		Class.forName("com.hzw.learn.springboot.javabase.classload.HZWTest_1");
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
