package com.hzw.learn.springboot.springbase.AOP.CGLIB;

import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import org.springframework.cglib.beans.BeanMap;
import org.springframework.cglib.proxy.*;
//import org.springframework.cglib.proxy.*;

import java.lang.reflect.Method;
import java.util.Random;

@FixMethodOrder(MethodSorters.JVM)
public class TestCGLIB {

    @Test
    public void cglibByParent_test() {
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(HzwHello.class);
        enhancer.setCallback(new HzwMethodInterceptor());
        HzwHello hello = (HzwHello) enhancer.create();
        HzwHello hello2 = (HzwHello) enhancer.create();
        /**
        * 这里的hello对象，反编译后：{@link HzwHello$$EnhancerByCGLIB$$b2f16848}
        **/
        hello.sayHello();
    }

    @Test
    public void cglibWithInterface_test() throws InterruptedException {
        Enhancer enhancer = new Enhancer();
        enhancer.setInterfaces(new Class[]{HelloApi.class});
        enhancer.setSuperclass(HzwHello.class);
        enhancer.setCallback(new HzwMethodInterceptor());
        HelloApi hello = (HelloApi) enhancer.create();
        /**
         * 这里的hello对象，反编译后：{@link HzwHello$$EnhancerByCGLIB$$cbdf3262}
         **/
        hello.sayHello();
    }

    @Test
    public void test3() throws InterruptedException {
        Enhancer enhancer = new Enhancer();
        enhancer.setCallbackFilter(new CallbackFilter() {
            @Override
            public int accept(Method method) {
                System.out.println(method.getName() + "  do filter...");
                return new Random().nextInt(3);
            }
        });
        enhancer.setSuperclass(HzwHello.class);
        enhancer.setCallbacks(new Callback[]{
                new MethodInterceptor() {
                    @Override
                    public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
                        System.out.println("MethodInterceptro 1");
                        return null;
                    }
                },
                new MethodInterceptor() {
                    @Override
                    public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
                        System.out.println("MethodInterceptro 2");
                        return null;
                    }
                },
                new MethodInterceptor() {
                    @Override
                    public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
                        System.out.println("MethodInterceptro 3");
                        return null;
                    }
                }
        });
        HzwHello hello = (HzwHello) enhancer.create();
        /**
         * 这里的hello对象，反编译后：{@link HzwHello$$EnhancerByCGLIB$$dc02cf00}
         **/
//        Thread.sleep(100000);
        hello.sayHello();
    }

    @Test
    public void duplicateClassErrTest() throws InterruptedException {
        Enhancer enhancer = new Enhancer();
        HzwHello h1 = new HzwHello();
        for (int i=0; i<100 ;i++){
            new Thread(new Runnable() {
                @Override
                public void run() {
                    for (int b=0; true; b++) {

                        enhancer.setSuperclass(h1.getClass());
                        enhancer.setCallback(new MethodInterceptor() {
                            @Override
                            public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
                                return methodProxy.invoke(h1, objects);
                            }
                        });
                        HzwHello a = (HzwHello) enhancer.create();
//                        a.sayHello();
                    }
                }
            }).start();
        }

        Thread.sleep(2000);
    }


}
