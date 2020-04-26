package com.hzw.learn.springboot.dubbo.router;

import java.util.ArrayList;
import java.util.Date;

import org.apache.dubbo.config.ApplicationConfig;
import org.apache.dubbo.config.ReferenceConfig;
import org.apache.dubbo.config.RegistryConfig;
import org.apache.dubbo.rpc.cluster.loadbalance.RoundRobinLoadBalance;

import com.hzw.learn.springboot.dubbo.router.api.RouterHiApi;
import com.hzw.learn.springboot.dubbo.router.common.HzwReferenceConfig;


public class Consumer_API {
	
    private static String zookeeperHost = System.getProperty("zookeeper.address", "127.0.0.1");
    private static String zookeeperUrl = "zookeeper://" + zookeeperHost + ":2181";
    private static String APPNAME = System.getProperty("appname", "consumer-api");
    private static String APPGROUP = System.getProperty("appgroup", "dubbo");
    
    private static String APPTAG = System.getProperty("apptag");
    private static String APPSERVERGROUP = System.getProperty("appservergroup", "111");
    private static String APPSERVERVERSION = System.getProperty("appserverversion", "*");

    public static void main(String[] args) throws InterruptedException {

    	ApplicationConfig application = new ApplicationConfig(APPNAME);
    	
    	RegistryConfig registry_zookeeper = new RegistryConfig(zookeeperUrl);
    	registry_zookeeper.setGroup(APPGROUP);
    	
        ArrayList<RegistryConfig> registries = new ArrayList<>();
        registries.add(registry_zookeeper);
        
//        ReferenceConfig<RouterHiApi> reference = new ReferenceConfig<>();
        HzwReferenceConfig<RouterHiApi> reference = new HzwReferenceConfig<>();
//        reference.setQueuename("22222222");
        
        reference.setApplication(application);
        reference.setRegistries(registries);		// 配置注册中心列表，多个注册中心
        reference.setInterface(RouterHiApi.class);
        reference.setTag(APPTAG);
//        reference.setTag("tag1");
        reference.setVersion(APPSERVERVERSION);	// version匹配所有版本，调用hi3时会负载分配到匹配到的服务上
        reference.setGroup(APPSERVERGROUP);	// 指定group
        reference.setLoadbalance(RoundRobinLoadBalance.NAME);

        RouterHiApi hi = reference.get();

        while(true) {
            System.out.print("Consumer:tag[" + APPTAG + "] call received:");
            System.out.println(hi.hi("Consumer_Api[" + APPTAG + "] data" + new Date()));
        	Thread.sleep(1000);
        }
        
    }
}
