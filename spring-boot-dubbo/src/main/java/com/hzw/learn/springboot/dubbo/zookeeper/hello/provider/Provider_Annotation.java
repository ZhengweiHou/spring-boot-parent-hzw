package com.hzw.learn.springboot.dubbo.zookeeper.hello.provider;

import java.util.concurrent.CountDownLatch;

import org.apache.dubbo.config.spring.context.annotation.EnableDubbo;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

public class Provider_Annotation {
    public static void main(String[] args) throws Exception {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(ProviderConfiguration.class);
        context.start();

        System.out.println("dubbo service started");
        new CountDownLatch(1).await();
    }

    @Configuration
    @EnableDubbo(scanBasePackages = "com.hzw.learn.springboot.dubbo.zookeeper.hello.provider")
    @PropertySource("classpath:/com/hzw/learn/springboot/dubbo/zookeeper/hello/provider/dubbo-provider.properties")
    static class ProviderConfiguration {
    }
}
