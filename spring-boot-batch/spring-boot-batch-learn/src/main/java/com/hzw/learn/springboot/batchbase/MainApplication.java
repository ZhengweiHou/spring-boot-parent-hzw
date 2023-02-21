package com.hzw.learn.springboot.batchbase;

import com.fasterxml.jackson.databind.jsontype.PolymorphicTypeValidator;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class MainApplication {
    static ApplicationContext context;
    public static void main(String[] args) throws Exception {
        context = new ClassPathXmlApplicationContext("service-context.xml");
        System.err.println("service running ......");

    }
}
