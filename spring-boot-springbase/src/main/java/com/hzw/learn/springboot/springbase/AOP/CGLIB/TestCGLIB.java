package com.hzw.learn.springboot.springbase.AOP.CGLIB;

import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import org.springframework.cglib.proxy.Enhancer;

@FixMethodOrder(MethodSorters.JVM)
public class TestCGLIB {

    @Test
    public void cglibByParent_test() {
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(HzwHello.class);
        enhancer.setCallback(new HzwMethodInterceptor());
        HzwHello hello = (HzwHello) enhancer.create();
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

}
