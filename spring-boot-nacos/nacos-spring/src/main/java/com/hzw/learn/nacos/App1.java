package com.hzw.learn.nacos;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import javax.annotation.PostConstruct;

public class App1 {

    @PostConstruct
    public void init() {
    }

    static ApplicationContext context;
    public static void main(String[] args) throws Exception {
        context = new ClassPathXmlApplicationContext("app1.xml");
        System.err.println("service running ......");
    }
}
