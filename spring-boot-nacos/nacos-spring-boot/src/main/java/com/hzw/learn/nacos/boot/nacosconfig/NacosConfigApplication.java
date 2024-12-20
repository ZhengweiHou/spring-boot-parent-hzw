package com.hzw.learn.nacos.boot.nacosconfig;

import com.alibaba.nacos.spring.context.annotation.config.EnableNacosConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import javax.annotation.PostConstruct;
import javax.swing.*;


@SpringBootApplication
public class NacosConfigApplication {

    public static void main(String[] args) throws Exception {
        ConfigurableApplicationContext application
                = SpringApplication.run(NacosConfigApplication.class, args);
        application.registerShutdownHook();
    }
}
