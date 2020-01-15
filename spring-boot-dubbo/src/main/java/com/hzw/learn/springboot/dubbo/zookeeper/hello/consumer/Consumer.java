package com.hzw.learn.springboot.dubbo.zookeeper.hello.consumer;

import org.apache.dubbo.config.ApplicationConfig;
import org.apache.dubbo.config.ReferenceConfig;
import org.apache.dubbo.config.RegistryConfig;

import com.hzw.learn.springboot.dubbo.zookeeper.hello.provider.Hi;

public class Consumer {
    private static String zookeeperHost = System.getProperty("zookeeper.address", "127.0.0.1");

    public static void main(String[] args) {
        ReferenceConfig<Hi> reference = new ReferenceConfig<>();
        reference.setApplication(new ApplicationConfig("first-dubbo-consumer"));
        reference.setRegistry(new RegistryConfig("zookeeper://" + zookeeperHost + ":2181"));
        reference.setInterface(Hi.class);
        reference.setVersion("1.0.0");
        
        Hi hi1 = reference.get();
        String hello = hi1.sayhi("dubbo");
        System.out.println(hello);
        
        reference = new ReferenceConfig<>();
        reference.setApplication(new ApplicationConfig("first-dubbo-consumer"));
        reference.setRegistry(new RegistryConfig("zookeeper://" + zookeeperHost + ":2181"));
        reference.setInterface(Hi.class);
        reference.setVersion("2.0.0");
        Hi hi2 = reference.get();
        
        String hello2 = hi2.sayhi("dubbo2");
        System.out.println(hello2);
        
    }
}
