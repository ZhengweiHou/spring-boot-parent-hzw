package com.hzw.learn.springboot.springbase.DIAndIOC.IOCHello;

/**
 * @ClassName HelloStaticFactory
 * @Description 静态工厂方法
 * @Author houzw
 * @Date 2020/6/30
 **/
public class HelloStaticFactory {
    public static HelloApi newInstance(String msg){
        System.out.println("==静态工厂方法==");
        return new HelloImpl(msg);
    }
}
