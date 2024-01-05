package com.hzw.learn.springboot.springbootbase.AutoWareWithFactoryBean;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;

@SpringBootApplication
//@ComponentScan(basePackages = { "com.hzw.learn.springboot.springbootbase.AutoWareWithFactoryBean" })
@ImportResource("classpath:AutoWareWithFactoryBean.xml")
public class Application {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}
}
