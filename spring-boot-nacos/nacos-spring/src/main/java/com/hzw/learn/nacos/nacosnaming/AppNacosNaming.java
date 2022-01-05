package com.hzw.learn.nacos.nacosnaming;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import javax.annotation.PostConstruct;


class AppNacosNaming {

    @PostConstruct
    public void init() {
    }

    static ApplicationContext context;
    public static void main(String[] args) throws Exception {
        context = new ClassPathXmlApplicationContext("appnacosnaming.xml");

        System.err.println("service running ......");
//        System.in.read();
        Thread.sleep(100*1000);
    }
}
