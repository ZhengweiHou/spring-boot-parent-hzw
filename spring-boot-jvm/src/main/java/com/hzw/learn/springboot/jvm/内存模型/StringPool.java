package com.hzw.learn.springboot.jvm.内存模型;

import java.lang.reflect.Field;

public class StringPool {
    public static void main(String[] args) throws NoSuchFieldException, IllegalAccessException {
        String hello = "hello";
        Field value =  String.class.getDeclaredField("value");
        value.setAccessible(true);
        value.set(hello,"hehe".toCharArray());

        System.out.println("hello");
    }
}
