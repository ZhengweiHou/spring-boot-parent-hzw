package com.hzw.learn.springboot.dubbo.serviceExportAndReference.reference;

import com.hzw.learn.springboot.dubbo.serviceExportAndReference.api.Hi;
import org.apache.dubbo.config.ApplicationConfig;
import org.apache.dubbo.config.ReferenceConfig;
import org.apache.dubbo.config.RegistryConfig;
import org.apache.dubbo.rpc.service.GenericException;
import org.apache.dubbo.rpc.service.GenericService;
import org.junit.Test;

import java.util.ArrayList;
import java.util.concurrent.CompletableFuture;

public class Reference_Generic {

    @Test
    public void test() throws InterruptedException {
        ApplicationConfig application = new ApplicationConfig("Reference_Generic");

        RegistryConfig registry_zookeeper = new RegistryConfig("zookeeper://127.0.0.1:2181");
        ArrayList<RegistryConfig> registries = new ArrayList<>();
        registries.add(registry_zookeeper);

        ReferenceConfig<GenericService> reference = new ReferenceConfig<>();
        reference.setApplication(application);
        reference.setRegistries(registries);
        reference.setGroup("*");
        reference.setVersion("*");
        reference.setInit(true);        // true:饿汉模式；false:懒汉模式

        reference.setInterface("com.hzw.learn.springboot.dubbo.serviceExportAndReference.api.Hi");
        reference.setGeneric(true);									//是否支持泛化调用，我们这里肯定要设置为true
        GenericService genericService = reference.get();

        while(true) {
//            System.out.println(hi3.sayhi("Consumer_Api"));
            System.out.println(
                genericService.$invoke("sayhi", new String[]{String.class.getName()}, new Object[]{"Reference_Generic"})
            );
            Thread.sleep(1000);
        }

    }

}
