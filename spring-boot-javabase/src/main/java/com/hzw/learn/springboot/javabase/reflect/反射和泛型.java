package com.hzw.learn.springboot.javabase.reflect;

import java.lang.reflect.Array;
import java.util.Map;

public class 反射和泛型 {
	public static void main(String[] args) {
		// 通过反射创建String数组
		String[] array1 = (String[])Array.newInstance(String.class, 5);
		// 使用泛型，避免使用时的强制类型转换
		String[] array2 = HzwArray.newInstance(String.class, 5);
		
		String[][] array3 = HzwArray.newInstance(String[].class, 5);
		String[][] array4 = (String[][]) HzwArray.newInstance(array2.getClass(), 5); // ????一脸懵逼????
		
		String temp = "";
		String[] a1 = HzwArray.newInstance(String.class, 5);
		String[] a2 = HzwArray.newInstance(temp.getClass(), 5);
		
	}
}

class HzwArray{
	public static <T> T[] newInstance(Class<T> clazz, int length) {
		return (T[]) Array.newInstance(clazz, length);
	}

}

