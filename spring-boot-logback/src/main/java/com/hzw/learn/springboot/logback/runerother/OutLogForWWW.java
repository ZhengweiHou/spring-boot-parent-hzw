package com.hzw.learn.springboot.logback.runerother;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

public class OutLogForWWW implements Runnable {

	private static final Logger logger = LoggerFactory.getLogger("WWW");

	@Override
	public void run(){
		
		long sleep = 5*1000;
		
		while(true){
			logger.trace("message.....TRACE WWW");
			logger.debug("message.....DEBUG WWW");
			logger.info("message.....INFO  WWW");
			logger.warn("message.....WARN  WWW");
			logger.error("message.....ERROR WWW");
			
			try {
				Thread.sleep(sleep);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		
	}

}
