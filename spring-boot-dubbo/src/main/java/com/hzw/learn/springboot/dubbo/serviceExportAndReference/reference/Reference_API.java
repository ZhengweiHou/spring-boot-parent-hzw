package com.hzw.learn.springboot.dubbo.serviceExportAndReference.reference;

import com.hzw.learn.springboot.dubbo.serviceExportAndReference.api.Hi;
import org.apache.dubbo.config.ApplicationConfig;
import org.apache.dubbo.config.ProtocolConfig;
import org.apache.dubbo.config.ReferenceConfig;
import org.apache.dubbo.config.RegistryConfig;

import java.util.ArrayList;


public class Reference_API {

    public static void main(String[] args) throws InterruptedException {
    	ApplicationConfig application = new ApplicationConfig("consumer-api");
    	
    	RegistryConfig registry_zookeeper = new RegistryConfig("zookeeper://127.0.0.1:2181");

//        registry_zookeeper.setProtocol("http"); // 指定注册协议，没指定则默认是dubbo

        ArrayList<RegistryConfig> registries = new ArrayList<>();
        registries.add(registry_zookeeper);

        ReferenceConfig<Hi> reference = new ReferenceConfig<>();
        reference.setApplication(application);
        reference.setRegistries(registries);
        reference.setGroup("*");
        reference.setInterface(Hi.class);
        reference.setVersion("*");
        reference.setInit(true);        // true:饿汉模式；false:懒汉模式

    	Hi hi3 = reference.get();
        
        while(true) {
            System.out.println(hi3.sayhi("Consumer_Api"));
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
