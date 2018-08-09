package com.hzw.learn.springboot.logback.runer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class LevelTest implements CommandLineRunner {

	private static final Logger logger = LoggerFactory.getLogger(LevelTest.class);

	@Override
	public void run(String... args) throws Exception {

		while(true){
			Thread.sleep(5 * 1000);
			logger.trace("trace message.....");
			logger.debug("debug message.....");
			logger.info("info message.....");
			logger.warn("warn message.....");
			logger.error("error message.....");
		}
		
	}

}
