package com.hzw.learn.springboot.javabase.reflect;

import java.lang.reflect.Array;
import java.lang.reflect.Field;
import java.lang.reflect.Parameter;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.Arrays;
import java.util.Map;

public class Field和Parameter获取泛型信息 {
	public static void main(String[] args) throws Exception {
		Field field1 = HzwClass.class.getField("arg1");
		Field field2 = HzwClass.class.getField("arg2");
		Parameter param1 = (Parameter) Array.get(HzwClass.class.getMethod("methodTest", Map.class, String.class).getParameters(), 0);
		Parameter param2 = (Parameter) Array.get(HzwClass.class.getMethod("methodTest", Map.class, String.class).getParameters(), 1);
		
		Type ft1 = field1.getGenericType();
		Type ft2 = field2.getGenericType();
		Type pt1 = param1.getParameterizedType();
		Type pt2 = param2.getParameterizedType();
		
		System.out.println("is ParameterizedType:\n"
			+ "  " + (ft1 instanceof ParameterizedType)	// true
			+ "  " + (ft2 instanceof ParameterizedType)	// false
			+ "  " + (pt1 instanceof ParameterizedType)	// true
			+ "  " + (pt2 instanceof ParameterizedType)	// false
		);
		
		ParameterizedType ppt = (ParameterizedType)pt1;
		Type rawType = ppt.getRawType();
		Type[] arguments = ppt.getActualTypeArguments();
		System.out.println("原始类型：" + rawType);
		System.out.println("泛型信息：");
		Arrays.asList(arguments).stream().forEach(type -> {
			System.out.print(type + "  ");
			System.out.println(type instanceof ParameterizedType);
		});
	}
	
/*
输出内容：
 is ParameterizedType:
  true  false  true  false
原始类型：interface java.util.Map
泛型信息：
class java.lang.String  false
java.util.Map<java.lang.String, java.lang.Integer>  true
 */
	
}

class HzwClass{
	public Map<String, String> arg1;
	public String arg2;
	public void methodTest(Map<String, Map<String,Integer>> arg1, String arg2) {};
}