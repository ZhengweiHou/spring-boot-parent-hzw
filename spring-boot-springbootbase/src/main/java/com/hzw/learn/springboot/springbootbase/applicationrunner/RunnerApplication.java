package com.hzw.learn.springboot.springbootbase.applicationrunner;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = { "com.hzw.learn.springboot.springbootbase.applicationrunner" })
public class RunnerApplication {

	public static void main(String[] args) {
		args = new String[]{"1","2","3"};
		SpringApplication.run(RunnerApplication.class, args);
		System.out.println("==");
	}
}
