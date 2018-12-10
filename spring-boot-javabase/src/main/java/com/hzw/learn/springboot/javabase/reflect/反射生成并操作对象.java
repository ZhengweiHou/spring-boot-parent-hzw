package com.hzw.learn.springboot.javabase.reflect;

import java.lang.reflect.InvocationTargetException;

public class 反射生成并操作对象 {

	public static void main(String[] args) throws NoSuchMethodException, SecurityException, InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		Class<ClassTest> clazz = ClassTest.class;
		/////////////////创建对象///////////////////
		// 使用Class对象创建
		clazz.newInstance();
		// 使用Constructor对象创建
		clazz.getConstructor(String.class).newInstance("呵呵");
		
		
	}
	
}
