package com.hzw.learn.springboot.logback.runer2;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

public class LevelTest2 implements Runnable {

	private static final Logger logger = LoggerFactory.getLogger(LevelTest2.class);

	@Override
	public void run(){
		
		long sleep = 5*1000;
		
		while(true){
			logger.trace("message.....TRACE 2222");
			logger.debug("message.....DEBUG 2222");
			logger.info("message.....INFO  2222");
			logger.warn("message.....WARN  2222");
			logger.error("message.....ERROR 2222");
			
			try {
				Thread.sleep(sleep);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		
	}

}
