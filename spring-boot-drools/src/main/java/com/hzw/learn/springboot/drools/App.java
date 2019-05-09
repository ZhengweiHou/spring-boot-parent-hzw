package com.hzw.learn.springboot.drools;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class App {

	public static void main(String[] args) {

		App obj = new App();
		obj.run();

	}

	private void run() {

		String[] springConfig = { "classpath:service-context.xml" };

		ApplicationContext context = new ClassPathXmlApplicationContext(springConfig);
	}
}
