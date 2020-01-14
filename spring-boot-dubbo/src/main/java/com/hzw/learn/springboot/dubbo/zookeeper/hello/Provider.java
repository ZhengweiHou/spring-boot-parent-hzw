package com.hzw.learn.springboot.dubbo.zookeeper.hello;

import java.util.concurrent.CountDownLatch;

import org.apache.dubbo.config.ApplicationConfig;
import org.apache.dubbo.config.RegistryConfig;
import org.apache.dubbo.config.ServiceConfig;

public class Provider {
    private static String zookeeperHost = System.getProperty("zookeeper.address", "127.0.0.1");

    public static void main(String[] args) throws Exception {
        ServiceConfig<Hi> service = new ServiceConfig<>();
        service.setApplication(new ApplicationConfig("first-dubbo-provider"));
        service.setRegistry(new RegistryConfig("zookeeper://" + zookeeperHost + ":2181"));
        service.setInterface(Hi.class);
        service.setRef(new HiImpl("张三"));
        service.setVersion("1.0.0");	// 第一个版本的服务
        service.export();

        System.out.println("dubbo service started");
        new CountDownLatch(1).await();
    }
}
