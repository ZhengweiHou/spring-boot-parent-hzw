package com.hzw.learn.springboot.dubbo.hello.consumer;

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
        
        hi.sayHi3("z3");
        
//        new CountDownLatch(1).await();	// 让进程坚持住，不要死！！让我在zk里看到你
    }

    @Configuration
    @EnableDubbo(scanBasePackages = "com.hzw.learn.springboot.dubbo.hello.consumer")
    @ComponentScan(value = {"com.hzw.learn.springboot.dubbo.hello.consumer"})
//	@PropertySource("classpath:/com/hzw/learn/springboot/dubbo/hello/consumer/dubbo-consumer.properties")
	@PropertySource("classpath:hello/dubbo-consumer.properties")
    static class ConsumerConfiguration {

    }
	
}

@Component("annotatedConsumer")
class GreetingServiceConsumer{
	@Reference(version = "2.0.0")	// dubbo注解获取版本为2.0.0的Hi服务
	private Hi hi;
	

	@Reference(version = "*")	// version匹配所有版本，调用hi3时会负载分配到匹配到的服务上
	private Hi hi3;
	
	public String sayHi(String name) {
		return hi.sayhi(name);
	}
	
	public void sayHi3(String name) {
		System.out.println("hi3 say:" + hi3.sayhi(name));

		System.out.println("hi3 say:" + hi3.sayhi(name));

		System.out.println("hi3 say:" + hi3.sayhi(name));

		System.out.println("hi3 say:" + hi3.sayhi(name));
		
		
	}
}