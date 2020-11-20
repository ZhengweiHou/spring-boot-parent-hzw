package com.hzw.learn.springboot.hzwrpc.proxy;

import java.lang.reflect.Proxy;

public class ProxyMain {
    public static void main(String[] args) {

        ProxyInvocationHandler handler = new ProxyInvocationHandler();
        handler.a = new AImpl();

        A a = (A) Proxy.newProxyInstance(ProxyMain.class.getClassLoader(),new Class[]{A.class},handler);
        a.hi("haha");
    }

}
