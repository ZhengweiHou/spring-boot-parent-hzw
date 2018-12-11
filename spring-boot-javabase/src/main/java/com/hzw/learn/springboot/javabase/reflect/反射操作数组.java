package com.hzw.learn.springboot.javabase.reflect;

import java.lang.reflect.Array;

public class 反射操作数组 {
	public static void main(String[] args) {
		Object arrayTest = 
			Array.newInstance(String.class, 2);
		Array.set(arrayTest, 0, "hhh");
		Array.set(arrayTest, 1, "zzz");
		System.out.println(""
			+ "   0:" + Array.get(arrayTest, 0)
			+ "   1:" + Array.get(arrayTest, 1)
		);
		
		
	}
}
