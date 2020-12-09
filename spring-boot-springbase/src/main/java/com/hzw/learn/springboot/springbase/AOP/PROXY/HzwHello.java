package com.hzw.learn.springboot.springbase.AOP.PROXY;

public class HzwHello implements HelloApi{
    @Override
    public void sayHello() {
        System.out.println("Hello world!");
    }
}
