package com.hzw.learn.sofa1;

import com.alipay.sofa.rpc.config.ProviderConfig;
import com.alipay.sofa.rpc.config.RegistryConfig;
import com.alipay.sofa.rpc.config.ServerConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;

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
//        serverConfig.setVirtualHost("localhost");
//        serverConfig.setVirtualPort(8001);
        serverConfig.setVirtualHost(env.getProperty("vhost"));
        serverConfig.setVirtualPort(Integer.valueOf(env.getProperty("vport")));
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
