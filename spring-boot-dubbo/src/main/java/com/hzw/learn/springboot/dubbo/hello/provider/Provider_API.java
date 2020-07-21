package com.hzw.learn.springboot.dubbo.hello.provider;

import java.util.ArrayList;
import java.util.concurrent.CountDownLatch;

import org.apache.dubbo.config.ApplicationConfig;
import org.apache.dubbo.config.ConfigCenterConfig;
import org.apache.dubbo.config.MetadataReportConfig;
import org.apache.dubbo.config.ProtocolConfig;
import org.apache.dubbo.config.RegistryConfig;
import org.apache.dubbo.config.ServiceConfig;
import org.apache.dubbo.config.spring.ServiceBean;
import org.apache.dubbo.rpc.cluster.loadbalance.RoundRobinLoadBalance;

public class Provider_API {
    private static String zookeeperHost = System.getProperty("zookeeper.address", "127.0.0.1"); // 获取-D参数，没有取默认值
    private static String consulHost = System.getProperty("consul.address", "127.0.0.1");
    
    private static String zookeeperUrl = "zookeeper://" + zookeeperHost + ":2181";
    
//    private static String group_value="hzw2";
    private static String group_value="dubbo";
    
    

    public static void main(String[] args) throws Exception {

    	// ======应用配置======
    	ApplicationConfig applicationConfig = new ApplicationConfig("provider_api");
    	
    	// 在线运维-QOS 端口 dubbo.application.qos.port=33333
//    	applicationConfig.setQosPort(33333); 
    	
    	// QOS 安全 dubbo.application.qos.accept.foreign.ip=false
    	applicationConfig.setQosAcceptForeignIp(false); // 拒绝远程主机命令
    	
    	// ======注册中心配置======
    	RegistryConfig registry_zookeeper = new RegistryConfig(zookeeperUrl);
    	registry_zookeeper.setGroup(group_value); // 注册中心级别的组别，zk下会创建对应根目录
//        RegistryConfig registry_consul = new RegistryConfig("consul://" + consulHost + ":8500");
//        registry_consul.setCheck(false);
    	// 组装一个注册中心集合
        ArrayList<RegistryConfig> registries = new ArrayList<>();
        registries.add(registry_zookeeper);
//        registries.add(registry_consul);	
        
        // ======元数据配置======
        MetadataReportConfig metadata = new MetadataReportConfig();
        metadata.setAddress(zookeeperUrl);
        metadata.setGroup(group_value);
        metadata.getMetaData().put("hhhhhhhhzzzzzzzzwww", "1111111122222");
        
        // ======配置中心======
        ConfigCenterConfig configCenter = new ConfigCenterConfig();
        configCenter.setAddress(zookeeperUrl);
        configCenter.setGroup(group_value);

    	// ======协议配置======
        ProtocolConfig protocol = new ProtocolConfig("dubbo",20881); // 协议配置
//        protocol.setStatus("spring,registry,server,memory,load,datasource,threadpool");	// 开启状态检查扩展
        protocol.setTelnet("cd,ps,select,log,ls,clear,count,invoke,exit,help,trace,pwd,shutdown,status");	// 开启telnet扩展
        
        
        // ======注册服务配置======
    	// 创建一个服务配置
        ServiceConfig<Hi> service = new ServiceConfig<>();
//        ServiceBean<Hi> service = new ServiceBean<>(); // 整合spring，内有spring的监听事件功能？？？

        /* ServiceConfig会先从下列三个对象中获取并初始化配置，优先级：ProviderConfig > ModuleConfig > ApplicationConfig
         * 详见{@link org.apache.dubbo.config.ServiceConfig#completeCompoundConfigs()} */
        service.setApplication(applicationConfig); // Registries、Monitor
//      service.setModule(module);     // Registries、Monitor
//      service.setProvider(provider); // Application、Module、Registries、Monitor、Protocols、ConfigCenter


        // 设置服务要注册的注册中心
        //service.setRegistry(registry_zookeeper);  // 设置单个注册中心
        service.setRegistries(registries);	// 设置多个注册中心集合
        // 设置元数据中心
        service.setMetadataReportConfig(metadata);
        // 设置配置中心
        service.setConfigCenter(configCenter);
        // 设置协议
        service.setProtocol(protocol);
        // service级别的组别，不是zk的根目录，要注意
        service.setGroup("2222");
        service.setTag("hhh");
        // 设置服务接口
        service.setInterface(Hi.class);
        // 设置服务实现类
        service.setRef(new HiImpl("张三"));
        service.setTag("xxx");
        // 设置服务版本（实际上就是一个标签，可供消费者过滤选择）
        service.setVersion("1.0.0");
        // 负载方式（实现方式？？）
//        service.setLoadbalance(RandomLoadBalance.NAME);
        service.setLoadbalance(RoundRobinLoadBalance.NAME);
        // 好了，先配置这样吧，让服务到注册中心报到吧
        service.export();


        System.out.println("dubbo service started");
        new CountDownLatch(1).await();
    }
}
