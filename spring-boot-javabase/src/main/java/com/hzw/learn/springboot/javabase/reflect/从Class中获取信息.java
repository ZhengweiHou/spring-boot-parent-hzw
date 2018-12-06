package com.hzw.learn.springboot.javabase.reflect;

import java.lang.reflect.Executable;
import java.util.Arrays;

public class 从Class中获取信息 {
	public static void main(String[] args) {
		Class<ClassTest> clazz = ClassTest.class;

		Arrays.asList(
				clazz.getFields()
		).forEach(System.out::println);
		Arrays.asList(
				clazz.getDeclaredFields()
		).forEach(System.out::println);
		
		clazz.getConstructors();
		clazz.getDeclaredConstructors();
		
		clazz.getMethods();
		clazz.getDeclaredMethods();
	}
}
