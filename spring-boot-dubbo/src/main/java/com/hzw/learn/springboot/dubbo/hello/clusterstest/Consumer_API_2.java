package com.hzw.learn.springboot.dubbo.hello.clusterstest;

import com.hzw.learn.springboot.dubbo.hello.provider.Hi;
import org.apache.dubbo.config.ApplicationConfig;
import org.apache.dubbo.config.ReferenceConfig;
import org.apache.dubbo.config.RegistryConfig;
import org.apache.dubbo.rpc.cluster.loadbalance.RoundRobinLoadBalance;


public class Consumer_API_2 {
    private static String zookeeperHost = System.getProperty("zookeeper.address", "127.0.0.1");

    public static void main(String[] args) throws InterruptedException {
    	// ======应用配置======
    	ApplicationConfig application = new ApplicationConfig("consumer-api");
    	
    	RegistryConfig registry_zookeeper = new RegistryConfig("zookeeper://" + zookeeperHost + ":2181");
        
        ReferenceConfig<Hi> reference = new ReferenceConfig<>();
        reference = new ReferenceConfig<>();
        reference.setApplication(application);
        reference.setRegistry(registry_zookeeper);
        reference.setLoadbalance(RoundRobinLoadBalance.NAME);
        reference.setInterface(Hi.class);

    	Hi hi3 = reference.get();
        
        while(true) {
            System.out.println(hi3.sayhi("consumer_2"));
        	Thread.sleep(100);
        }
        
//        System.out.println(hi3.sayhi("dubbo3"));
//        System.out.println(hi3.sayhi("dubbo3"));
//        System.out.println(hi3.sayhi("dubbo3"));
//        System.out.println(hi3.sayhi("dubbo3"));
//        System.out.println(hi3.sayhi("dubbo3"));
//        hi,dubbo3,I'm 张三
//        hi,dubbo3,I'm HiImpl
//        hi,dubbo3,I'm HiImpl
//        hi,dubbo3,I'm 张三
//        hi,dubbo3,I'm HiImpl
     
//        new CountDownLatch(1).await();	// 让进程坚持住，不要死！！让我在zk里看到你
    }
}
