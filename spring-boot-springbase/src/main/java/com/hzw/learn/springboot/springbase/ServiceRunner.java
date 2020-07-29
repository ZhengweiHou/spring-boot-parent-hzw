package com.hzw.learn.springboot.springbase;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class ServiceRunner {

	static ApplicationContext context;
	public static void main(String[] args) throws Exception {
		context = new ClassPathXmlApplicationContext("service-context.xml");

		System.err.println("service running ......");
//        System.in.read(); // hold on the process
	}
}
