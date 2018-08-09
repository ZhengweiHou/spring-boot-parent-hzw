package com.hzw.learn.springboot.logback.runer3;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

public class ExceptionTest implements Runnable {

	private static final Logger logger = LoggerFactory.getLogger(ExceptionTest.class);

	@Override
	public void run(){
		
		long sleep = 5*1000;
		
		while(true){
			try {
				this.createException();
			} catch (ArithmeticException e) {
				e.printStackTrace(); // 此处日志不能输出到日志文件中
				logger.error("",e); // 这样才能将异常输出到日志文件中
			}
			try {
				Thread.sleep(sleep);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		
	}
	
	private void createException() throws ArithmeticException{
		int a = 1/0;
	}

}
