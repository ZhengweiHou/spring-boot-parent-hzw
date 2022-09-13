package com.hzw.learn.sofa_consumer2multiprovider.server;

import com.alipay.sofa.rpc.config.ProviderConfig;
import com.alipay.sofa.rpc.config.RegistryConfig;
import com.alipay.sofa.rpc.config.ServerConfig;
import com.hzw.learn.ext.HelloService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;

import java.lang.management.ManagementFactory;
import java.lang.management.RuntimeMXBean;
import java.util.Optional;

@Configuration
public class Server2Config {
    @Autowired
    Environment env;

    @Bean
    public String jvmProcessID() {
        RuntimeMXBean runtimeMXBean = ManagementFactory.getRuntimeMXBean();
        System.out.println(runtimeMXBean.getName());
        return runtimeMXBean.getName().split("@")[0];
    }

    @Bean
    public RegistryConfig registryConfig() {
        RegistryConfig registryConfig = new RegistryConfig();
        registryConfig.setProtocol("nacos");
//        registryConfig.setAddress("192.168.32.201:8848/public");
        registryConfig.setAddress(env.getProperty("nacos.host") + "/" + env.getProperty("nacos.namespace"));
//        registryConfig.setAddress("127.0.0.1:8848/test");
//        registryConfig.setHeartbeatPeriod(300);
        return registryConfig;
    }

    @Bean
    public ServerConfig serverConfig() {
        ServerConfig serverConfig = new ServerConfig();
        serverConfig.setPort(9989);
        System.out.println("vhost:" + env.getProperty("vhost") + " vport:" + env.getProperty("vport"));
        Optional.ofNullable(env.getProperty("vhost")).ifPresent(vhost -> serverConfig.setVirtualHost(vhost));
        Optional.ofNullable(env.getProperty("vport")).ifPresent(vport -> {
            serverConfig.setVirtualPort(Integer.valueOf(vport));
            serverConfig.setPort(Integer.valueOf(vport));
        });
        serverConfig.setProtocol("bolt"); // 默认bolt协议

        // serverConfig.setProtocol("h2c");
        serverConfig.setAdaptivePort(true); // 是否自适应端口

        System.out.println(serverConfig.toString());

        return serverConfig;
    }


    @Bean
    public HelloServiceImpl helloService() {
        return new HelloServiceImpl();
    }

    @Bean
    public void helloServiceExport() {
        ProviderConfig<HelloService> providerConfig = new ProviderConfig<>();
        providerConfig.setServer(serverConfig());
        providerConfig.setRegistry(registryConfig());
        providerConfig.setInterfaceId(HelloService.class.getName());
        providerConfig.setRef(helloService());
        // TODO 可以通过-Ddefault.uniqueId=xxx 指定全局uniqueId
//        Optional.ofNullable(env.getProperty("default.uniqueId")).ifPresent(uniqueid ->
//                providerConfig.setUniqueId(uniqueid)
//        );
        System.out.println("==> 导出服务：" + providerConfig.getInterfaceId());
        providerConfig.export();
        System.out.println("sofa port:" + providerConfig.getServer().get(0).getPort());
    }

    /**
     * sofa 暴露http协议服务
     */
//    @Bean(name = "httpServerConfig")
//    public ServerConfig httpServerConfig(){
//        ServerConfig serverConfig = new ServerConfig();
//
//        serverConfig.setPort(9988); // server的区分维度为ip+port，不包括协议类型，应此此处的port需要和其他协议的port分开
//        serverConfig.setAdaptivePort(true); // 是否自适应端口
//
//        System.out.println("vhost:" + env.getProperty("vhost") + " vport:" + env.getProperty("vport"));
//        Optional.ofNullable(env.getProperty("vhost")).ifPresent(vhost -> serverConfig.setVirtualHost(vhost));
//        Optional.ofNullable(env.getProperty("vport")).ifPresent(vport -> {
//            serverConfig.setVirtualPort(Integer.valueOf(vport) + 10);
//            serverConfig.setPort(Integer.valueOf(vport) + 10);
//            serverConfig.setAdaptivePort(true);
//        });
//        serverConfig.setProtocol(RpcConstants.PROTOCOL_TYPE_HTTP); // 指定http协议
//        System.out.println(serverConfig.toString());
//        return serverConfig;
//    }
//
//    @Bean
//    public ProviderConfig httpHelloServiceExport(@Autowired @Qualifier("httpServerConfig") ServerConfig httpServerConfig){
//        ProviderConfig<HelloService> providerConfig = new ProviderConfig<>();
//        providerConfig.setServer(httpServerConfig);
//        providerConfig.setRegistry(registryConfig());
//        providerConfig.setInterfaceId(HelloService.class.getName());
//        providerConfig.setRef(helloService());
////        providerConfig.setUniqueId("hhh");
//        System.out.println("==> 导出http服务：" + providerConfig.getInterfaceId());
//        providerConfig.export();
//        System.out.print("sofa port:" + providerConfig.getServer().get(0).getPort());
//        System.out.println(" eg: curl " + httpServerConfig.getBoundHost() + ":" + httpServerConfig.getPort() + "/com.hzw.learn.sofa_base.sofa1.HelloService/hello2");
//        return providerConfig;
//    }


}
