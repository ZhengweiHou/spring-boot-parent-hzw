package com.hzw.learn.springboot.logback.runerother;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class OutLogForHHH implements Runnable {

	private static final Logger logger = LoggerFactory.getLogger("HHH");

	@Override
	public void run(){
		
		long sleep = 5*1000;
		
		while(true){
			logger.trace("message.....TRACE HHH");
			logger.debug("message.....DEBUG HHH");
			logger.info ("message.....INFO  HHH");
			logger.warn ("message.....WARN  HHH");
			logger.error("message.....ERROR HHH");
			try {
				Thread.sleep(sleep);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}
