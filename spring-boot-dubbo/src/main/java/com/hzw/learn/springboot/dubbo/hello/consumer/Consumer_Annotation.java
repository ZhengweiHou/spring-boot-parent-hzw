package com.hzw.learn.springboot.dubbo.hello.consumer;

import java.util.concurrent.CountDownLatch;

import org.apache.dubbo.config.annotation.Reference;
import org.apache.dubbo.config.spring.context.annotation.EnableDubbo;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

import com.hzw.learn.springboot.dubbo.hello.provider.Hi;


public class Consumer_Annotation {

    public static void main(String[] args) throws InterruptedException {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(ConsumerConfiguration.class);
        context.start();
        
        
        GreetingServiceConsumer hi = context.getBean(GreetingServiceConsumer.class);
        String hello = hi.sayHi("consume_anno 1");
        System.out.println("result: " + hello);
        
        String hello2 = hi.sayHi2("consume_anno 2");
        System.out.println("result: " + hello2);
        
        hi.sayHi3("z3");
        
//        new CountDownLatch(1).await();	// 让进程坚持住，不要死！！让我在zk里看到你
    }

    @Configuration
    @EnableDubbo(scanBasePackages = "com.hzw.learn.springboot.dubbo.hello.consumer")
    @PropertySource("classpath:/com/hzw/learn/springboot/dubbo/hello/consumer/dubbo-consumer.properties")
    @ComponentScan(value = {"com.hzw.learn.springboot.dubbo.hello.consumer"})
    static class ConsumerConfiguration {

    }
	
}

@Component("annotatedConsumer")
class GreetingServiceConsumer{
	@Reference(version = "2.0.0")	// dubbo注解获取版本为2.0.0的Hi服务
	private Hi hi;
	
	@Reference(version = "1.0.0")
	private Hi hi2;
	
	@Reference(version = "*")	// version匹配所有版本，调用hi3时会负载分配到匹配到的服务上
	private Hi hi3;
	
	public String sayHi(String name) {
		return hi.sayhi(name);
	}
	
	public String sayHi2(String name) {
		return hi2.sayhi(name);
	}
	
	public void sayHi3(String name) {
		System.out.println("hi3 say:" + hi3.sayhi(name));

		System.out.println("hi3 say:" + hi3.sayhi(name));

		System.out.println("hi3 say:" + hi3.sayhi(name));

		System.out.println("hi3 say:" + hi3.sayhi(name));
		
		
	}
}