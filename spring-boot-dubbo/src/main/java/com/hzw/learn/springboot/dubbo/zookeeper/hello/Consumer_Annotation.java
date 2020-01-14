package com.hzw.learn.springboot.dubbo.zookeeper.hello;

import org.apache.dubbo.config.annotation.Reference;
import org.apache.dubbo.config.spring.context.annotation.EnableDubbo;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

public class Consumer_Annotation {

    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(ConsumerConfiguration.class);
        context.start();
        GreetingServiceConsumer hi = context.getBean(GreetingServiceConsumer.class);
        String hello = hi.sayHi("zookeeper");
        System.out.println("result: " + hello);
        
        String hello2 = hi.sayHi("zookeeper2");
        System.out.println("result: " + hello2);
    }

    @Configuration
    @EnableDubbo(scanBasePackages = "com.hzw.learn.springboot.dubbo.zookeeper.hello")
    @PropertySource("classpath:/dubbo/zookeeper/hello/dubbo-consumer.properties")
    @ComponentScan(value = {"com.hzw.learn.springboot.dubbo.zookeeper.hello"})
    static class ConsumerConfiguration {

    }
	
}


class GreetingServiceConsumer{
	@Reference(version = "2.0.0")	// dubbo注解获取版本为2.0.0的Hi服务
	private Hi hi;
	
	@Reference(version = "1.0.0")
	private Hi hi2;
	
	public String sayHi(String name) {
		return hi.sayhi(name);
	}
	
	public String sayHi2(String name) {
		return hi2.sayhi(name);
	}
}