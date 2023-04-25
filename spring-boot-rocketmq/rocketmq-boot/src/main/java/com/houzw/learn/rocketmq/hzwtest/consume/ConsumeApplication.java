package com.houzw.learn.rocketmq.hzwtest.consume;

import org.apache.rocketmq.spring.core.RocketMQTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.ComponentScan;

import javax.annotation.Resource;

@SpringBootApplication
@ComponentScan(basePackages = { "com.houzw.learn.rocketmq.hzwtest.consume" })
public class ConsumeApplication implements CommandLineRunner {

	@Resource
	private RocketMQTemplate rocketMQTemplate;

	@Autowired
	private HzwRocketMQListener hzwRocketMQListener;

	public static void main(String[] args) {
		ConfigurableApplicationContext ctx = SpringApplication.run(ConsumeApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
//		rocketMQTemplate.getProducer().re
	}
}
