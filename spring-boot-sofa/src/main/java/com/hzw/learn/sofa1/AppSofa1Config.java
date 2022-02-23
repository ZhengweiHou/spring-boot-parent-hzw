package com.hzw.learn.sofa1;

import com.alipay.lookout.api.Counter;
import com.alipay.lookout.api.Registry;
import com.alipay.lookout.common.utils.NetworkUtil;
import com.alipay.sofa.rpc.common.RpcConstants;
import com.alipay.sofa.rpc.config.ProviderConfig;
import com.alipay.sofa.rpc.config.RegistryConfig;
import com.alipay.sofa.rpc.config.ServerConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.lang.management.ManagementFactory;
import java.lang.management.RuntimeMXBean;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

@Configuration
public class AppSofa1Config {
    @Autowired
    Environment env;

    @Bean
    public String jvmProcessID(){
        RuntimeMXBean runtimeMXBean = ManagementFactory.getRuntimeMXBean();
        System.out.println(runtimeMXBean.getName());
        return runtimeMXBean.getName().split("@")[0];
    }

    @Bean
    public RegistryConfig registryConfig(){
        RegistryConfig registryConfig = new RegistryConfig();
        registryConfig.setProtocol("nacos");
//        registryConfig.setAddress("192.168.32.201:8848/public");
        registryConfig.setAddress(env.getProperty("nacos.host") + "/" + env.getProperty("nacos.namespace"));
        registryConfig.setHeartbeatPeriod(30);
        return registryConfig;
    }

    @Bean
    public ServerConfig serverConfig(){
        ServerConfig serverConfig = new ServerConfig();
        System.out.println("vhost:" + env.getProperty("vhost") + " vport:" + env.getProperty("vport"));
        Optional.ofNullable(env.getProperty("vhost")).ifPresent(vhost -> serverConfig.setVirtualHost(vhost));
        Optional.ofNullable(env.getProperty("vport")).ifPresent(vport -> serverConfig.setVirtualPort(Integer.valueOf(vport)));
        serverConfig.setProtocol("bolt"); // 默认bolt协议
        serverConfig.setPort(9999);
        // serverConfig.setProtocol("h2c");
         serverConfig.setAdaptivePort(true); // 是否自适应端口
        System.out.println(serverConfig.toString());
        return serverConfig;
    }

    @Bean
    public ServerConfig httpServerConfig(){
        ServerConfig serverConfig = new ServerConfig();
        System.out.println("vhost:" + env.getProperty("vhost") + " vport:" + env.getProperty("vport"));
        Optional.ofNullable(env.getProperty("vhost")).ifPresent(vhost -> serverConfig.setVirtualHost(vhost));
        Optional.ofNullable(env.getProperty("vport")).ifPresent(vport -> serverConfig.setVirtualPort(Integer.valueOf(vport)));
        serverConfig.setProtocol(RpcConstants.PROTOCOL_TYPE_HTTP); // 指定http协议
        serverConfig.setPort(9999);
        serverConfig.setAdaptivePort(false); // 是否自适应端口
        System.out.println(serverConfig.toString());
        return serverConfig;
    }


    @Bean
    @Scope
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
        System.out.println("sofa port:" + providerConfig.getServer().get(0).getPort());
    }

    @Bean
    public void httpHelloServiceExport(){
        ProviderConfig<HelloService> providerConfig = new ProviderConfig<>();
        providerConfig.setServer(httpServerConfig());
        providerConfig.setRegistry(registryConfig());
//        providerConfig.setInterfaceId(HelloService.class.getName());
        providerConfig.setInterfaceId("hello");
        providerConfig.setRef(helloService());
        System.out.println("==> 导出http服务：" + providerConfig.getInterfaceId());
        providerConfig.export();
        System.out.println("sofa port:" + providerConfig.getServer().get(0).getPort());
    }




}
