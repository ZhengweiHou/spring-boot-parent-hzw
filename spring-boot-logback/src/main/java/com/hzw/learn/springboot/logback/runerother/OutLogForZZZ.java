package com.hzw.learn.springboot.logback.runerother;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

public class OutLogForZZZ implements Runnable {

	private static final Logger logger = LoggerFactory.getLogger("ZZZ");

	@Override
	public void run(){
		
		long sleep = 5*1000;
		
		while(true){
			logger.trace("message.....TRACE ZZZ侯");
			logger.debug("message.....DEBUG ZZZ正");
			logger.info("message.....INFO  ZZZ伟");
			logger.warn("message.....WARN  ZZZ帅");
			logger.error("message.....ERROR ZZZ锅");
			
			try {
				Thread.sleep(sleep);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		
	}

}
