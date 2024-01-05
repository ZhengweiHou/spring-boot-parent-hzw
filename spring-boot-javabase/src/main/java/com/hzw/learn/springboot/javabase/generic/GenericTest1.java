package com.hzw.learn.springboot.javabase.generic;

import org.springframework.util.ObjectUtils;

import java.lang.reflect.Field;

/**
 * @ClassName GenericTest1
 * @Description TODO
 * @Author houzw
 * @Date 2023/8/29
 **/
public class GenericTest1 {
    public static void main(String[] args) throws NoSuchFieldException {
        new Integer("0000");
        hello(new Integer(5));
        hello("hello");
    }

    public static <T> void hello(T t) throws NoSuchFieldException {
        doSomething(t);
    }

    public static void doSomething(Object t){
        Class<?> clazz = t.getClass();
        Field[] fs = clazz.getDeclaredFields();
        System.out.println(t.getClass());
        System.out.println(t);
    }
}
