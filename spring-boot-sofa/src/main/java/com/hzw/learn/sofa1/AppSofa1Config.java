package com.hzw.learn.sofa1;

import com.alipay.sofa.rpc.config.ProviderConfig;
import com.alipay.sofa.rpc.config.RegistryConfig;
import com.alipay.sofa.rpc.config.ServerConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;

import java.util.Optional;

@Configuration
public class AppSofa1Config {
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
    public ServerConfig serverConfig(){
        ServerConfig serverConfig = new ServerConfig();
        System.out.println("vhost:" + env.getProperty("vhost") + " vport:" + env.getProperty("vport"));
        Optional.ofNullable(env.getProperty("vhost")).ifPresent(vhost -> serverConfig.setVirtualHost(vhost));
        Optional.ofNullable(env.getProperty("vport")).ifPresent(vport -> serverConfig.setVirtualPort(Integer.valueOf(vport)));
        serverConfig.setProtocol("bolt"); // 默认bolt协议
        // serverConfig.setProtocol("h2c");
        // serverConfig.setAdaptivePort(true); // 是否自适应端口
        System.out.println(serverConfig.toString());
        return serverConfig;
    }

    @Bean
    public HelloServiceImpl helloService(){
        return new HelloServiceImpl();
    }

    @Bean
    public void helloServiceExport(){
        ProviderConfig<HelloService> providerConfig = new ProviderConfig<>();
        providerConfig.setServer(serverConfig());
        providerConfig.setRegistry(registryConfig());
        providerConfig.setInterfaceId(HelloService.class.getName());
        providerConfig.setRef(helloService());
        System.out.println("==> 导出服务：" + providerConfig.getInterfaceId());
        providerConfig.export();
    }




}
