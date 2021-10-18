package com.hzw.learn.springboot.logback.runer1;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

public class LevelTest1 implements Runnable {

	private static final Logger logger = LoggerFactory.getLogger(LevelTest1.class);

	@Override
	public void run() {

		long sleep = 5 * 1000;

		while (true) {
			logger.trace("message.....TRACE 1111");
			logger.debug("message.....DEBUG 1111");
			logger.info("message.....INFO  1111");
			logger.warn("message.....WARN  1111");
			logger.error("message.....ERROR 1111");

			try {
				Thread.sleep(sleep);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}

	}

}
