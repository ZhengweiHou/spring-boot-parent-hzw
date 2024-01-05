package com.hzw.learn.nacos.nacosconfig_origin;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import javax.annotation.PostConstruct;


public class NacosConfigApplication {

    @PostConstruct
    public void init() {
    }

    static ApplicationContext context;
    public static void main(String[] args) throws Exception {

        System.setProperty("enableSwitchWhite","true");

        System.setProperty("nacosAddress","127.0.0.1:8848/test");
//        System.setProperty("nacosNamespace","test");
//        System.setProperty("nacosUsername","nacos");
//        System.setProperty("nacosPassword","bmFjb3MK");

        System.setProperty(WhiteListHolder.white_data_key,"white:white");


        context = new ClassPathXmlApplicationContext("appnacosconfig_origin.xml");
        System.err.println("service running ......");

//        System.in.read();
        Thread.sleep(1000*1000);
    }
}
