package com.hzw.learn.sofa2;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.PropertySource;

@SpringBootApplication
@PropertySource("classpath:appSofa2.properties")
public class AppSofa2 {
    public static void main(String[] args) {
        SpringApplication.run(AppSofa2.class,args);
    }
}
