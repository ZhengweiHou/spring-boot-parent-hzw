package com.hzw.learn.springboot.javabase.reflect;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class 反射生成并操作对象 {

	public static void main(String[] args) throws NoSuchMethodException, SecurityException, InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchFieldException {
		Class<ClassTest> clazz = ClassTest.class;
		/////////////////创建对象///////////////////
		// 使用Class对象创建
		clazz.newInstance();
		// 使用Constructor对象创建
		ClassTest classTest = 
		clazz.getConstructor(String.class).newInstance("呵呵");
		
		Method method = clazz.getMethod("publicMethod", String.class);
		if(!method.isAccessible())
			method.setAccessible(false);
		method.invoke(new ClassTest(), "HZW");
		
		
		Field field = clazz.getField("publicStr");
		// 取值
		field.get(classTest);
		// 赋值
		field.set(classTest, "hzw");
	}
	
}
