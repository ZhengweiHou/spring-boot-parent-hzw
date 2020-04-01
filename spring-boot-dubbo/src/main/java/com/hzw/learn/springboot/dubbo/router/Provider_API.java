package com.hzw.learn.springboot.dubbo.router;

import java.util.ArrayList;
import java.util.concurrent.CountDownLatch;

import org.apache.dubbo.config.ApplicationConfig;
import org.apache.dubbo.config.MetadataReportConfig;
import org.apache.dubbo.config.ProtocolConfig;
import org.apache.dubbo.config.RegistryConfig;
import org.apache.dubbo.config.ServiceConfig;
import org.apache.dubbo.rpc.cluster.loadbalance.RoundRobinLoadBalance;

import com.hzw.learn.springboot.dubbo.router.api.RouterHiApi;
import com.hzw.learn.springboot.dubbo.router.api.RouterHiImpl;
import com.hzw.learn.springboot.dubbo.router.common.HzwServiceConfig;

public class Provider_API {
    private static String zookeeperHost = System.getProperty("zookeeper.address", "127.0.0.1");
    private static String consulHost = System.getProperty("consul.address", "127.0.0.1");
    
    private static String zookeeperUrl = "zookeeper://" + zookeeperHost + ":2181";
    

    public static void main(String[] args) throws Exception {

    	ApplicationConfig applicationConfig = new ApplicationConfig("provider_api");
    	applicationConfig.setQosPort(33333); 
    	
    	RegistryConfig registry_zookeeper = new RegistryConfig(zookeeperUrl);
    	registry_zookeeper.setGroup("eg.router");
        ArrayList<RegistryConfig> registries = new ArrayList<>();
        registries.add(registry_zookeeper);

        ProtocolConfig protocol = new ProtocolConfig("dubbo",20880);
        protocol.setTelnet("cd,ps,select,log,ls,clear,count,invoke,exit,help,trace,pwd,shutdown,status");
        
        MetadataReportConfig metadata = new MetadataReportConfig();
        metadata.setAddress(zookeeperUrl);
        metadata.getMetaData().put("hhhhhhhhzzzzzzzzwww", "1111111122222");
        
//        ServiceConfig<RouterHiApi> service = new ServiceConfig<>();
        HzwServiceConfig<RouterHiApi> service = new HzwServiceConfig<>();
        service.setQueuename("123123123");
        
//        service.setProvider(provider);
//        service.setMonitor(monitor);
        service.setApplication(applicationConfig);
        
        service.setRegistries(registries);	
        service.setMetadataReportConfig(metadata);
        service.setProtocol(protocol);
        service.setGroup("2222");
        service.setInterface(RouterHiApi.class);
        service.setRef(new RouterHiImpl());
        service.setVersion("2.0.0");
        service.setLoadbalance(RoundRobinLoadBalance.NAME);
        
        
        service.export();
        
        
        System.out.println("dubbo service started");
        new CountDownLatch(1).await();
    }
}
