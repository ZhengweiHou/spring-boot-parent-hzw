package com.hzw.learn.springboot.dubbo.hello.provider;

import org.apache.dubbo.config.*;
import org.apache.dubbo.rpc.cluster.loadbalance.RoundRobinLoadBalance;

import java.util.ArrayList;
import java.util.concurrent.CountDownLatch;

public class Provider_API_temp {
    private static String zookeeperHost = "192.168.104.123"; // 获取-D参数，没有取默认值

    private static String zookeeperUrl = "zookeeper://" + zookeeperHost + ":2181";
    
    private static String group_value="dubbo";
    
    

    public static void main(String[] args) throws Exception {

    	// ======应用配置======
    	ApplicationConfig applicationConfig = new ApplicationConfig("provider_api");

    	// ======注册中心配置======
    	RegistryConfig registry_zookeeper = new RegistryConfig(zookeeperUrl);
    	registry_zookeeper.setGroup(group_value); // 注册中心级别的组别，zk下会创建对应根目录


    	// ======协议配置======
        ProtocolConfig protocol = new ProtocolConfig("dubbo",20881); // 协议配置

        // ======注册服务配置======
    	// 创建一个服务配置
        ServiceConfig<Hi> service = new ServiceConfig<>();

        service.setApplication(applicationConfig); // Registries、Monitor


        // 设置服务要注册的注册中心
        service.setRegistry(registry_zookeeper);	// 设置多个注册中心集合

        // 设置协议
        service.setProtocol(protocol);
        // 设置服务接口
        service.setInterface(Hi.class);
        // 设置服务实现类
        service.setRef(new HiImpl("张三"));
        // 负载方式（实现方式？？）
//        service.setLoadbalance(RandomLoadBalance.NAME);
        service.setLoadbalance(RoundRobinLoadBalance.NAME);

        service.export();


        System.out.println("dubbo service started");
        new CountDownLatch(1).await();
    }
}
