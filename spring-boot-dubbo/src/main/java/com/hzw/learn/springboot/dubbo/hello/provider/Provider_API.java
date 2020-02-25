package com.hzw.learn.springboot.dubbo.hello.provider;

import java.util.ArrayList;
import java.util.concurrent.CountDownLatch;

import org.apache.dubbo.config.ApplicationConfig;
import org.apache.dubbo.config.ProtocolConfig;
import org.apache.dubbo.config.RegistryConfig;
import org.apache.dubbo.config.ServiceConfig;

public class Provider_API {
    private static String zookeeperHost = System.getProperty("zookeeper.address", "127.0.0.1");
    private static String consulHost = System.getProperty("consul.address", "127.0.0.1");
    

    public static void main(String[] args) throws Exception {
    	// 创建一个服务配置
        ServiceConfig<Hi> service = new ServiceConfig<>();
        // 设置服务名
        service.setApplication(new ApplicationConfig("first-dubbo-provider"));
        // 注册中心配置
    	RegistryConfig registry_zookeeper = new RegistryConfig("zookeeper://" + zookeeperHost + ":2181");
        RegistryConfig registry_consul = new RegistryConfig("consul://" + consulHost + ":8500");
        registry_consul.setCheck(false);
    	
        ArrayList<RegistryConfig> registries = new ArrayList<>();
        registries.add(registry_zookeeper);
        registries.add(registry_consul);
        
        // 设置服务要注册的注册中心
//        service.setRegistry(registry_zookeeper);
        service.setRegistries(registries);
        
        ProtocolConfig protocol = new ProtocolConfig("dubbo",20881); // 协议配置
        service.setProtocol(protocol);
        
        
        // 设置服务接口
        service.setInterface(Hi.class);
        // 设置服务实现类
        service.setRef(new HiImpl("张三"));
        // 设置服务版本（实际上就是一个标签，可供消费者过滤选择）
        service.setVersion("3.0.0");	// 第一个版本的服务
        service.setGroup("dubbo_hzw");
        
        // 好了，先配置这样吧，让服务到注册中心报到吧
        service.export();

        System.out.println("dubbo service started");
        new CountDownLatch(1).await();
    }
}
