package com.hzw.learn.springboot.springbootbase.configtest;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
// 扫描指定包
@ComponentScan(basePackages= {"com.hzw.learn.springboot.springbootbase.configtest"})
public class ConfigTestApplication {
	public static void main(String[] args) {
		SpringApplication.run(ConfigTestApplication.class, args);
	}
}
