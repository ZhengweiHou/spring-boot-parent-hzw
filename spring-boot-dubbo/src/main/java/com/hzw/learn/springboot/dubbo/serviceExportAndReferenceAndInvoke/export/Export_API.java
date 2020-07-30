package com.hzw.learn.springboot.dubbo.serviceExportAndReferenceAndInvoke.export;

import com.hzw.learn.springboot.dubbo.serviceExportAndReferenceAndInvoke.api.Hi;
import com.hzw.learn.springboot.dubbo.serviceExportAndReferenceAndInvoke.api.HiImpl;
import org.apache.dubbo.config.*;
import org.junit.Test;

import java.util.ArrayList;
import java.util.concurrent.CountDownLatch;

public class Export_API {

    @Test
    public void test1() throws Exception {

    	ApplicationConfig applicationConfig = new ApplicationConfig("provider_api");

    	RegistryConfig registry_zookeeper = new RegistryConfig("zookeeper://127.0.0.1:2181");

//        registry_zookeeper.setProtocol("http"); // 指定注册协议，没指定则默认是dubbo

    	registry_zookeeper.setGroup("dubbo");

        ArrayList<RegistryConfig> registries = new ArrayList<>();
        registries.add(registry_zookeeper);

        ProtocolConfig protocol = new ProtocolConfig("dubbo",20882); // 协议配置

        ServiceConfig<Hi> service = new ServiceConfig<>();
        service.setApplication(applicationConfig);
        service.setRegistries(registries);
        service.setProtocol(protocol);
        service.setInterface(Hi.class);
        service.setRef(new HiImpl("Exportor"));
//        service.setGroup("servicegroup1");
//        service.setVersion("1.0.0");

        service.export();


        System.out.println("dubbo service started");
        new CountDownLatch(1).await();



    }

    @Test
    public void test2() throws Exception {


    }
}
