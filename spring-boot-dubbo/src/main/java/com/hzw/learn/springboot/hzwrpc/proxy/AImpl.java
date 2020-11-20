package com.hzw.learn.springboot.hzwrpc.proxy;

public class AImpl implements  A{
    @Override
    public void hi(String name) {
        System.out.println(name);
    }
}
