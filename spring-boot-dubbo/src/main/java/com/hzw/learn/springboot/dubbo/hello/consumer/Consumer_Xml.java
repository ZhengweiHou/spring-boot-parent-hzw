package com.hzw.learn.springboot.dubbo.hello.consumer;

import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.hzw.learn.springboot.dubbo.hello.provider.Hi;

public class Consumer_Xml {
    public static void main(String[] args) throws InterruptedException {
//        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("/com/hzw/learn/springboot/dubbo/hello/consumer/dubbo-consumer.xml");
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("hello/dubbo-consumer.xml");
        context.start();
        
        Hi hiService = (Hi) context.getBean("hiService");
        
        while(true) {
            System.out.println(hiService.sayhi("Consumer_Xml"));
        	Thread.sleep(1000);
        }
    }
}
