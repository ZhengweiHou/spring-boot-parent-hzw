package com.hzw.learn.sofa2;

import com.alipay.sofa.rpc.config.ConsumerConfig;
import com.alipay.sofa.rpc.config.ProviderConfig;
import com.alipay.sofa.rpc.config.RegistryConfig;
import com.alipay.sofa.rpc.config.ServerConfig;
import com.hzw.learn.sofa1.HelloService;
import com.hzw.learn.sofa1.HelloServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;

@Configuration
public class AppSofa2Config {
    @Autowired
    Environment env;

    @Bean
    public RegistryConfig registryConfig(){
        RegistryConfig registryConfig = new RegistryConfig();
        registryConfig.setProtocol("nacos");
//        registryConfig.setAddress("192.168.32.201:8848/public");
        registryConfig.setAddress(env.getProperty("nacos.host") + "/" + env.getProperty("nacos.namespace"));
        return registryConfig;
    }

    @Bean
    public HelloService helloServiceRefer(){
        ConsumerConfig<HelloService> consumerConfig = new ConsumerConfig<>();
        consumerConfig.setId("helloconsumer_sofa2");
        consumerConfig.setProtocol("bolt"); // 默认bolt协议
        // consumerConfig.setProtocol("h2c");
        /* 代理的实现方式 可选SPI 如下，默认为javassist：
        jdk=com.alipay.sofa.rpc.proxy.jdk.JDKProxy
        javassist=com.alipay.sofa.rpc.proxy.javassist.JavassistProxy
        bytebuddy=com.alipay.sofa.rpc.proxy.bytebuddy.BytebuddyProxy*/
        consumerConfig.setProxy("jdk");

        consumerConfig.setRegistry(registryConfig());
        consumerConfig.setInterfaceId(HelloService.class.getName());


        return consumerConfig.refer();
    }



}
