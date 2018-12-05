package com.hzw.learn.springboot.javabase.classload;

import java.io.IOException;
import java.lang.reflect.Array;
import java.net.URL;
import java.util.Arrays;
import java.util.Enumeration;

public class 类加载器 {
	public static void main(String[] args) throws IOException {
		
		/* 系统类加载器 */
		ClassLoader systemLoader = ClassLoader.getSystemClassLoader();
		//sun.misc.Launcher
		System.out.println("系统类加载器：" + systemLoader );
		Enumeration<URL> em1 = systemLoader.getResources("");
		while(em1.hasMoreElements()) {
			System.out.println(em1.nextElement());
		}
		
		/* 扩展类加载器*/
		ClassLoader extensionLader = systemLoader.getParent();
		System.out.println("扩展类加载器：" + extensionLader);
		System.out.println("扩展类加载器的加载路径：" + System.getProperty("java.ext.dirs"));
		System.out.println("扩展类加载器的parent：" + extensionLader.getParent());
		
		/* 根加载器 */
		// 获取根类加载器所加载的全部URL数组
		URL[] urls = sun.misc.Launcher.getBootstrapClassPath().getURLs();
		Arrays.asList(urls).stream().forEach(System.out::println); //lambda数组遍历
		
	}
}
