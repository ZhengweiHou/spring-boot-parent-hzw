package com.hzw.learn.springboot.hzwrpc.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

public class ProxyInvocationHandler implements InvocationHandler {
    public A a;

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
//        return method.invoke(a,args);
        System.out.println("hehe");
        return method.invoke(a,args);
    }
}
