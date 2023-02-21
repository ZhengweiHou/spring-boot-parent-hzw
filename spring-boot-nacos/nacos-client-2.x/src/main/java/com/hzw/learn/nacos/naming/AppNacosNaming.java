package com.hzw.learn.nacos.naming;

import com.alibaba.nacos.api.NacosFactory;
import com.alibaba.nacos.api.exception.NacosException;
import com.alibaba.nacos.api.naming.NamingService;
import com.alibaba.nacos.api.naming.listener.NamingEvent;
import com.alibaba.nacos.api.naming.pojo.Instance;
import com.google.gson.Gson;
import org.junit.Before;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

class AppNacosNaming {

    String serverAddr;
    String namespace;

    NamingService namingService;
    public static void main(String[] args) throws Exception {

        AppNacosNaming namingApp = new AppNacosNaming();

        namingApp.init();
//        namingApp.registerInstanceTest();
        namingApp.namingSubscribeTest();

        Thread.sleep(1000 * 1000);

    }

    public void init() throws NacosException {
        this.serverAddr = "localhost:8148";
        this.namespace = "test";

        Properties properties = new Properties();
        properties.put("serverAddr", serverAddr);
        properties.put("namespace", namespace);
        namingService = NacosFactory.createNamingService(properties);
//        namingService = NacosFactory.createNamingService(serverAddr);

    }

    public void registerInstanceTest() throws NacosException {
        Instance instance = new Instance();
        instance.setIp("55.55.55.55");
        instance.setPort(9992);
        instance.setHealthy(true);
        instance.setWeight(2.0);
        instance.setEphemeral(true); // 是否临时实例

        Map<String, String> instanceMeta = new HashMap<>();
        instanceMeta.put("site", "et2");
        instance.setMetadata(instanceMeta);
        instance.setServiceName("testService");
        instance.setClusterName("testCluster");

        namingService.registerInstance("hzw.test.service1", instance);
    }

    public void getInstancesTest() throws NacosException {
        System.out.println(new Gson().toJson(
                namingService.getAllInstances("hzw.test.service1")
        ));
    }

    public void namingSubscribeTest() throws Exception {
        namingService.subscribe("hzw.test.service1", event -> {
            if (event instanceof NamingEvent) {
                System.out.println(((NamingEvent) event).getServiceName());
                System.out.println(((NamingEvent) event).getInstances());
            }
        });

    }

}
