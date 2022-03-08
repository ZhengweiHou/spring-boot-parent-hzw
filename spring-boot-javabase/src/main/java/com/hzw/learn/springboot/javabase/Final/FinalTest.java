package com.hzw.learn.springboot.javabase.Final;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

public class FinalTest {
	public static void main(String[] args) throws NoSuchFieldException, IllegalAccessException {
		TestBean bean1 = new TestBean();
		bean1.map1.put("a","a");

		Map map1 = new HashMap();
		map1.put("b","b");

//		bean1.map1 = map1;

		Field field = TestBean.class.getDeclaredField("map1");
		field.setAccessible(true);
		field.set(bean1,map1);

		System.out.println("hehe");
	}
}
