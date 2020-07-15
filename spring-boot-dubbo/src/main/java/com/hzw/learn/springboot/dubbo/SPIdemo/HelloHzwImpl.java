package com.hzw.learn.springboot.dubbo.SPIdemo;

/**
 * @ClassName HelloHzwImpl
 * @Description TODO
 * @Author houzw
 * @Date 2020/7/15
 **/
public class HelloHzwImpl implements HelloApi {
    @Override
    public void sayHello() {
        System.out.println("hello I'm Hzw:" + this.hashCode());
    }
}
