package com.hzw.learn.springboot.dubbo.hello.clusterstest;

import com.hzw.learn.springboot.dubbo.hello.provider.Hi;
import com.hzw.learn.springboot.dubbo.hello.provider.HiImpl;
import org.apache.dubbo.config.ApplicationConfig;
import org.apache.dubbo.config.ProtocolConfig;
import org.apache.dubbo.config.RegistryConfig;
import org.apache.dubbo.config.ServiceConfig;
import org.apache.dubbo.rpc.cluster.loadbalance.RoundRobinLoadBalance;

import java.util.concurrent.CountDownLatch;

public class Provider_API_4 {
    private static String zookeeperHost = System.getProperty("zookeeper.address", "127.0.0.1"); // 获取-D参数，没有取默认值

    private static String zookeeperUrl = "zookeeper://" + zookeeperHost + ":2181";
    
    private static String group_value="dubbo";
    private static int protocolPort = 20884;
    

    public static void main(String[] args) throws Exception {

    	// ======应用配置======
    	ApplicationConfig applicationConfig = new ApplicationConfig("provider_api");
    	
    	// ======注册中心配置======
    	RegistryConfig registry_zookeeper = new RegistryConfig(zookeeperUrl);
    	registry_zookeeper.setGroup(group_value); // 注册中心级别的组别，zk下会创建对应根目录


    	// ======协议配置======
        ProtocolConfig protocol = new ProtocolConfig("dubbo",protocolPort); // 协议配置

        // ======注册服务配置======
    	// 创建一个服务配置
        ServiceConfig<Hi> service = new ServiceConfig<>();
        service.setApplication(applicationConfig);
        service.setRegistry(registry_zookeeper);
        // 设置协议
        service.setProtocol(protocol);
        // 设置服务接口
        service.setInterface(Hi.class);
        // 设置服务实现类
        service.setRef(new HiImpl("Provider_4"));
        service.setLoadbalance(RoundRobinLoadBalance.NAME);
        service.export();

        System.out.println("dubbo service started");
        new CountDownLatch(1).await();
    }
}
