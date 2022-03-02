package com.hzw.learn.springboot.logback;

import com.hzw.learn.springboot.logback.runerother.OutLogForZZZ;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.hzw.learn.springboot.logback.runer1.LevelTest1;

@Component
public class RunStart implements CommandLineRunner {

	@Override
	public void run(String... arg0) throws Exception {
		System.out.println("run start ...");
//		System.err.println("13123123");
//		try {
//			ExceptionTest.createException();
//		} catch (Exception e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}

//		new Thread(new LevelTest1()).start();
//		new Thread(new LevelTest2()).start();
//		new Thread(new ExceptionTest()).start();
//		new Thread(new OutLogForHHH()).start();
		new Thread(new OutLogForZZZ()).start();
//		new Thread(new OutLogForWWW()).start();

	}

}
