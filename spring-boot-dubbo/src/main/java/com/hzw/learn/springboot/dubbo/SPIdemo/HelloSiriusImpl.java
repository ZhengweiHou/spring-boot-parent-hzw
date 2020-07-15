package com.hzw.learn.springboot.dubbo.SPIdemo;

/**
 * @ClassName HelloSiriusImpl
 * @Description TODO
 * @Author houzw
 * @Date 2020/7/15
 **/
public class HelloSiriusImpl implements HelloApi {
    @Override
    public void sayHello() {
        System.out.println("hello I'm Sirius:" + this.hashCode());
    }
}
