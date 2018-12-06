package com.hzw.learn.springboot.javabase.classload;

import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.Arrays;

public class URLClassLoader_test {
	public static void main(String[] args) throws MalformedURLException, InstantiationException, IllegalAccessException, ClassNotFoundException {
		URL[] urls = {
				// 使用本地文件
				new URL("file:/home/houzw/TEST/spring-boot-javabase-0.0.1-SNAPSHOT.jar"),
				// 使用网络文件
				new URL("https://github.com/ZhengweiHou/TankRun/raw/master/TankRun.jar")
			};
		URLClassLoader urlLoader = new URLClassLoader(urls);
		URL[] loadurls = urlLoader.getURLs();
		Arrays.asList(loadurls).stream().forEach(System.out::println);
		
		// 从本地文件中加载并实例化类
		Object object2 = urlLoader.loadClass("com.hzw.learn.springboot.javabase.classload.类加载和初始化").newInstance();
		System.out.println(object2);
		// 从网络远程文件中加载并实例化类
		Object object1 = urlLoader.loadClass("MyTankGame1_12.TankUtil").newInstance();
		System.out.println(object1);
		
		// 运行TankRun.jar中的坦克游戏
		urlLoader.loadClass("MyTankGame1_12.MyTankGameFrame").newInstance();
	}
}

/*
file:/home/houzw/TEST/spring-boot-javabase-0.0.1-SNAPSHOT.jar
https://github.com/ZhengweiHou/TankRun/raw/master/TankRun.jar
MyTankGame1_12.TankUtil@39aeed2f
com.hzw.learn.springboot.javabase.classload.类加载和初始化@724af044
 */
