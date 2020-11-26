package com.hzw.learn.springboot.dubbo.hello.provider;

import org.apache.dubbo.config.ApplicationConfig;
import org.apache.dubbo.config.ProtocolConfig;
import org.apache.dubbo.config.RegistryConfig;
import org.apache.dubbo.config.ServiceConfig;

import java.util.concurrent.CountDownLatch;

public class Provider_NoRegistry {

    private static Integer serverport = 20880;

    public static void main(String[] args) throws Exception {

    	ApplicationConfig applicationConfig = new ApplicationConfig("provider_api");

        RegistryConfig registry = new RegistryConfig("N/A");    // 不使用注册中心

        ProtocolConfig protocol = new ProtocolConfig("dubbo",serverport);

        ServiceConfig<Hi> service = new ServiceConfig<>();
        service.setApplication(applicationConfig);


        service.setRegistry(registry);
        service.setProtocol(protocol);
        service.setInterface(Hi.class);
        service.setRegister(false);

        service.setRef(new HiImpl("梅超疯_1"));

        service.export();



        System.out.println("dubbo service started");
        new CountDownLatch(1).await();
    }
}
