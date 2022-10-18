package com.hzw.learn.springboot.javabase.reflect.methodtest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

import javax.validation.Valid;
import java.lang.reflect.Method;

/**
 * @ClassName A
 * @Description TODO
 * @Author houzw
 * @Date 2022/9/20
 **/
public class A {
    public String method1(@Valid String arg1, @Autowired String arg2){
        return null;
    }

    public static void main(String[] args) throws NoSuchMethodException {
        Method method = A.class.getMethod("method1", String.class, String.class);
        method.getParameterAnnotations();
        System.out.println("");
    }
}
