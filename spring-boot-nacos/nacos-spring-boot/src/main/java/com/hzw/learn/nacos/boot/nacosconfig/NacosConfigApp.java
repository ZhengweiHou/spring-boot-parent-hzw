package com.hzw.learn.nacos.boot.nacosconfig;

import com.alibaba.nacos.spring.context.annotation.config.EnableNacosConfig;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import javax.annotation.PostConstruct;


//@ComponentScan(basePackages = "com.hzw.learn.nacos.nacosconfig")
@EnableNacosConfig
public class NacosConfigApp {

    @PostConstruct
    public void init() {
    }

    static ApplicationContext context;
    public static void main(String[] args) throws Exception {
        System.out.println((int)Math.ceil(1/3000.0));

        context = new ClassPathXmlApplicationContext("appnacosconfig.xml");
        System.err.println("service running ......");

//        System.in.read();
        Thread.sleep(1000*1000);
    }
}
