package com.hzw.learn.nacos.naming;

import com.alibaba.nacos.api.NacosFactory;
import com.alibaba.nacos.api.exception.NacosException;
import com.alibaba.nacos.api.naming.NamingService;
import com.alibaba.nacos.api.naming.listener.NamingEvent;
import com.alibaba.nacos.api.naming.pojo.Instance;
import com.google.gson.Gson;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

@FixMethodOrder(MethodSorters.JVM)
public class NamingTest {
    String serverAddr;
    String namespace;

    NamingService namingService;

    @Before
    public void init() throws NacosException {
        this.serverAddr = "localhost:8148";
        this.namespace = "test";

        Properties properties = new Properties();
        properties.put("serverAddr", serverAddr);
        properties.put("namespace", namespace);
        // nacos 服务开启权限验证后需要提供用户名和密码
        properties.put("username","nacos");
        properties.put("password","nacos");
        namingService = NacosFactory.createNamingService(properties);
//        namingService = NacosFactory.createNamingService(serverAddr);
    }

    @Test
    public void registerInstanceTest() throws NacosException {
        Instance instance = new Instance();
        instance.setIp("55.55.55.55");
        instance.setPort(9998);
        instance.setHealthy(true);
        instance.setWeight(2.0);
        instance.setEphemeral(false); // 是否临时实例

        Map<String, String> instanceMeta = new HashMap<>();
        instanceMeta.put("site", "et2");
        instance.setMetadata(instanceMeta);
        instance.setServiceName("testService");
        instance.setClusterName("testCluster");

        namingService.registerInstance("hzw.test.service1", instance);
//        namingService.deregisterInstance("hzw.test.service1", instance);
    }

    @Test
    public void getInstancesTest() throws NacosException {
        System.out.println(new Gson().toJson(
                namingService.getAllInstances("hzw.test.service1",false) // subscribe 为 false 进行测试，否则将会优先查询客户端缓存
        ));
    }

    @Test
    public void namingSubscribeTest() throws Exception {
        namingService.subscribe("hzw.test.service1", event -> {
            if (event instanceof NamingEvent) {
                System.out.println(((NamingEvent) event).getServiceName());
                System.out.println(((NamingEvent) event).getInstances());
            }
        });

        Thread.sleep(1000 * 1000);
    }



}
