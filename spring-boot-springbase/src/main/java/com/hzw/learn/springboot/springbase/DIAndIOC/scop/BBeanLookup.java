package com.hzw.learn.springboot.springbase.DIAndIOC.scop;

import org.springframework.cglib.proxy.Enhancer;
import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.cglib.proxy.MethodProxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class BBeanLookup {
    public BBean createBBean(){
        // BBean is not interface is fail
//        return (BBean) Proxy.newProxyInstance(getClass().getClassLoader(), new Class[]{BBean.class}, new InvocationHandler() {
//            @Override
//            public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
//                System.out.println("proxy:" + proxy.getClass() + "." + method.getName());
//                return method.invoke(proxy,args);
//            }
//        });

        // CGlib
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(BBean.class);
        enhancer.setCallback(new MethodInterceptor() {
            @Override
            public Object intercept(Object obj, Method method, Object[] args1, MethodProxy proxy) throws Throwable {

                System.out.println("proxy:" + obj.getClass() + "." + method.getName());
                return proxy.invokeSuper(obj, args1);
            }
        });
        Object o = enhancer.create();


        System.out.println("BBeanLookup create BBean by cglib:" + o.getClass());
        return (BBean) o;
    }
}
