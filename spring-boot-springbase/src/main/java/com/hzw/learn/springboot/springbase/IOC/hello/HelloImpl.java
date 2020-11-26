package com.hzw.learn.springboot.springbase.IOC.hello;

/**
 * @ClassName HelloImpl
 * @Description TODO
 * @Author houzw
 * @Date 2020/6/29
 **/
public class HelloImpl implements HelloApi {

    private String msg;

    public HelloImpl(String msg) {
        this.msg = msg;
    }

    public HelloImpl() {
        this.msg = "hello sirius!";
    }

    public HelloImpl(String msg1, String msg2) {
        this.msg = msg1 + "+" + msg2;
    }

        @Override
    public void sayHello() {
        System.out.println(msg);
    }
}
