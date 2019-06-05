package com.hzw.learn.springboot.drools.start;

import org.springframework.boot.CommandLineRunner;
import org.springframework.core.Ordered;
import org.springframework.stereotype.Component;

@Component
public class StartService2 implements CommandLineRunner, Ordered{

	@Override
	public int getOrder() {
		return 2;
	}

	@Override
	public void run(String... args) throws Exception {
		System.out.println("Service start 2 .......");
		System.exit(0);
		
	}

}
