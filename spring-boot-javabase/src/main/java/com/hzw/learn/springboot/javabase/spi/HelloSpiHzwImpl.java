package com.hzw.learn.springboot.javabase.spi;

/**
 * @ClassName HelloSpiHzwImpl
 * @Description TODO
 * @Author houzw
 * @Date 2020/7/16
 **/
public class HelloSpiHzwImpl implements HelloSpiApi {
    @Override
    public void sayHello() {
        System.out.println("Hello I'm hzw");
    }
}
