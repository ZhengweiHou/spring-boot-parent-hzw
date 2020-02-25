package com.hzw.learn.springboot.dubbo.hello.provider;

import java.util.concurrent.CountDownLatch;


import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Provider_Xml {
    public static void main(String[] args) throws Exception {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("/com/hzw/learn/springboot/dubbo/hello/provider/dubbo-provider.xml");

        context.registerShutdownHook();
        context.start();

        System.out.println("dubbo service started");
        new CountDownLatch(1).await();
    }
}
