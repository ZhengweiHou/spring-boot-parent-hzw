package com.hzw.learn.nacos.nacosconfig;

import org.springframework.beans.factory.BeanFactoryUtils;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.core.env.Environment;

import javax.annotation.PostConstruct;
import java.lang.reflect.Method;


//@ComponentScan(basePackages = "com.hzw.learn.nacos.nacosconfig")
public class NacosConfigApp {

    @PostConstruct
    public void init() {
    }

    static ApplicationContext context;
    public static void main(String[] args) throws Exception {
        context = new ClassPathXmlApplicationContext("appnacosconfig.xml");
        System.err.println("service running ......");

        Environment env = context.getBean(Environment.class);
        String a = env.getProperty("a");
        String c = env.getProperty("c");
        System.out.println("a=" + a);

//        System.in.read();
//        Thread.sleep(1000*30);
    }
}
