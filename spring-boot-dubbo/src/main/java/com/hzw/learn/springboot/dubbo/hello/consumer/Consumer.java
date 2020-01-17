package com.hzw.learn.springboot.dubbo.hello.consumer;

import java.awt.List;
import java.util.ArrayList;
import java.util.concurrent.CountDownLatch;

import org.apache.dubbo.config.ApplicationConfig;
import org.apache.dubbo.config.ReferenceConfig;
import org.apache.dubbo.config.RegistryConfig;

import com.hzw.learn.springboot.dubbo.hello.provider.Hi;


public class Consumer {
    private static String zookeeperHost = System.getProperty("zookeeper.address", "127.0.0.1");

    public static void main(String[] args) throws InterruptedException {
    	ApplicationConfig application = new ApplicationConfig("first-dubbo-consumer");
    	
    	RegistryConfig registry_zookeeper = new RegistryConfig("zookeeper://" + zookeeperHost + ":2181");
        RegistryConfig registry_consul = new RegistryConfig("consul://" + zookeeperHost + ":8500");
    	
        ArrayList<RegistryConfig> registries = new ArrayList<>();
        registries.add(registry_consul);
        registries.add(registry_zookeeper);

        
        ReferenceConfig<Hi> reference = new ReferenceConfig<>();
        reference.setApplication(application);
//        reference.setRegistry(registry_zookeeper);	// 只配置一个注册中心
        reference.setRegistries(registries);		// 配置注册中心列表，多个注册中心
        reference.setInterface(Hi.class);
        reference.setVersion("1.0.0");
        
        Hi hi1 = reference.get();
        String hello = hi1.sayhi("dubbo");
        System.out.println(hello);
        
        reference = new ReferenceConfig<>();
        reference.setApplication(application);
//      reference.setRegistry(registry_zookeeper);	// 只配置一个注册中心
        reference.setRegistries(registries);		// 配置注册中心列表，多个注册中心
        reference.setInterface(Hi.class);
        reference.setVersion("2.0.0");
        Hi hi2 = reference.get();
        
        String hello2 = hi2.sayhi("dubbo2");
        System.out.println(hello2);
        
        reference = new ReferenceConfig<>();
        reference.setApplication(application);
//      reference.setRegistry(registry_zookeeper);	// 只配置一个注册中心
        reference.setRegistries(registries);		// 配置注册中心列表，多个注册中心
        reference.setInterface(Hi.class);
        reference.setVersion("*");	// version匹配所有版本，调用hi3时会负载分配到匹配到的服务上
        Hi hi3 = reference.get();
        
        while(true) {
            System.out.println(hi3.sayhi("dubbo3"));
        	Thread.sleep(1000);
        }
        
//        System.out.println(hi3.sayhi("dubbo3"));
//        System.out.println(hi3.sayhi("dubbo3"));
//        System.out.println(hi3.sayhi("dubbo3"));
//        System.out.println(hi3.sayhi("dubbo3"));
//        System.out.println(hi3.sayhi("dubbo3"));
//        hi,dubbo3,I'm 张三
//        hi,dubbo3,I'm HiImpl
//        hi,dubbo3,I'm HiImpl
//        hi,dubbo3,I'm 张三
//        hi,dubbo3,I'm HiImpl
     
//        new CountDownLatch(1).await();	// 让进程坚持住，不要死！！让我在zk里看到你
    }
}
