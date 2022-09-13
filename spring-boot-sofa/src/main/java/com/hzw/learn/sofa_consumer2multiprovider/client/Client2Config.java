package com.hzw.learn.sofa_consumer2multiprovider.client;

import com.alipay.sofa.rpc.config.ConsumerConfig;
import com.alipay.sofa.rpc.config.RegistryConfig;
import com.alipay.sofa.rpc.core.request.SofaRequest;
import com.hzw.learn.ext.HelloService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;

import java.util.List;

@Configuration
public class Client2Config {

    Logger logger = LoggerFactory.getLogger(Client2Config.class);

    @Autowired
    Environment env;

    @Bean
    public RegistryConfig registryConfig(){
        RegistryConfig registryConfig = new RegistryConfig();
        registryConfig.setProtocol("nacos");
        registryConfig.setAddress(env.getProperty("nacos.host") + "/" + env.getProperty("nacos.namespace"));
        return registryConfig;
    }

    @Bean
    public ConsumerRoute consumerRoute(){
        return new SimpleConsumerRoute();
    }

    @Bean
    public ConsumerUniqueIdRouteFactory<HelloService> helloServiceRefer(){
        ConsumerUniqueIdRouteFactory<HelloService> consumerFactory = new ConsumerUniqueIdRouteFactory<HelloService>();
        consumerFactory.setServiceInterface(HelloService.class);
        return consumerFactory;
    }
    




}
