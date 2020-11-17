package com.hzw.learn.springboot.dubbo.hello.consumer;

import com.hzw.learn.springboot.dubbo.hello.provider.Hi;
import org.apache.dubbo.config.ApplicationConfig;
import org.apache.dubbo.config.ReferenceConfig;
import org.apache.dubbo.rpc.cluster.LoadBalance;
import org.apache.dubbo.rpc.cluster.loadbalance.RoundRobinLoadBalance;


public class Consumer_NoRegistry {

    public static void main(String[] args) throws InterruptedException {
    	ApplicationConfig application = new ApplicationConfig("consumer-api");
    	
        ReferenceConfig<Hi> reference = new ReferenceConfig<>();
        reference.setApplication(application);

//        reference.setUrl("dubbo://127.0.0.1:20881;dubbo://127.0.0.1:20882");    // 设置Url进行直连，不通过注册中心
        reference.setUrl("dubbo://127.0.0.1:20881");    // 设置Url进行直连，不通过注册中心
        reference.setLoadbalance(RoundRobinLoadBalance.NAME); //roundrobin
        reference.setInterface(Hi.class);

    	Hi hi3 = reference.get();
        
        while(true) {
            System.out.println(hi3.sayhi("李四"));
        	Thread.sleep(2000);
        }
        
    }
}
