package com.hzw.learn.springboot.springbase.IOC.hello;

/**
 * @ClassName HelloInstanceFactory
 * @Description 实例工厂方法
 * @Author houzw
 * @Date 2020/6/30
 **/
public class HelloInstanceFactory {
    public HelloApi newInstance(String msg1, String msg2){
        System.out.println("==实例工厂方法==");
        return new HelloImpl(msg1, msg2);
    }
}
