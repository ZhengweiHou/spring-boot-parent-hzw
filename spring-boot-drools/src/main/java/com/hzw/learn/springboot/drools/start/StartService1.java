package com.hzw.learn.springboot.drools.start;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import com.hzw.learn.springboot.drools.demo1.Route1_Test;

@Component
@Order(value= 1)
public class StartService1 implements ApplicationRunner{
	
	@Autowired
	Route1_Test route1_Test;

	@Override
	public void run(ApplicationArguments args) throws Exception {
		// TODO Auto-generated method stub
		System.out.println("startService1..........");
		route1_Test.runRoute1();
	}

}
