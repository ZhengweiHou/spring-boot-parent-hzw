package com.hzw.learn.springboot.dubbo.hello.consumer;

import com.hzw.learn.springboot.dubbo.hello.provider.Hi;
import org.apache.dubbo.config.ApplicationConfig;
import org.apache.dubbo.config.ReferenceConfig;
import org.apache.dubbo.config.RegistryConfig;
import org.apache.dubbo.rpc.cluster.loadbalance.RoundRobinLoadBalance;

import java.util.ArrayList;


public class Consumer_API {
    private static String zookeeperHost = System.getProperty("zookeeper.address", "127.0.0.1");

    public static void main(String[] args) throws InterruptedException {
    	// ======应用配置======
    	ApplicationConfig application = new ApplicationConfig("consumer-api");
    	
    	
    	RegistryConfig registry_zookeeper = new RegistryConfig("zookeeper://" + zookeeperHost + ":2181");
//        RegistryConfig registry_consul = new RegistryConfig("consul://" + zookeeperHost + ":8500");
//    	registry_consul.setCheck(false);
    	
        ArrayList<RegistryConfig> registries = new ArrayList<>();
        registries.add(registry_zookeeper);
//        registries.add(registry_consul);
        
        ReferenceConfig<Hi> reference = new ReferenceConfig<>();
//        reference.setApplication(application);
////        reference.setRegistry(registry_zookeeper);	// 只配置一个注册中心
//        reference.setRegistries(registries);		// 配置注册中心列表，多个注册中心
//        reference.setInterface(Hi.class);
//        reference.setVersion("1.0.0");
//        
//        Hi hi1 = reference.get();
//        String hello = hi1.sayhi("dubbo");
//        System.out.println(hello);
//        
//        reference = new ReferenceConfig<>();
//        reference.setApplication(application);
////      reference.setRegistry(registry_zookeeper);	// 只配置一个注册中心
//        reference.setRegistries(registries);		// 配置注册中心列表，多个注册中心
//        reference.setInterface(Hi.class);
//        reference.setVersion("1.0.0");
//        Hi hi2 = reference.get();
//        
//        String hello2 = hi2.sayhi("dubbo2");
//        System.out.println(hello2);
        
        reference = new ReferenceConfig<>();
        reference.setApplication(application);
//      reference.setRegistry(registry_zookeeper);	// 只配置一个注册中心
        reference.setRegistries(registries);		// 配置注册中心列表，多个注册中心
        reference.setGroup("*");	// 指定group
        reference.setLoadbalance(RoundRobinLoadBalance.NAME);
        reference.setInterface(Hi.class);
        reference.setVersion("*");	// version匹配所有版本，调用hi3时会负载分配到匹配到的服务上
        reference.setTag("zzz");
        reference.setInit(true);        // true:饿汉模式；false:懒汉模式

    	Hi hi3 = reference.get();
        
        while(true) {
            System.out.println(hi3.sayhi("Consumer_Api"));
        	Thread.sleep(2000);
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
