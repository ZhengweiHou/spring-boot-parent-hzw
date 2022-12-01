package com.hzw.learn.Test;

import com.alipay.sofa.rpc.common.RpcConstants;
import com.alipay.sofa.rpc.config.ProviderConfig;
import com.alipay.sofa.rpc.config.RegistryConfig;
import com.alipay.sofa.rpc.config.ServerConfig;
import com.hzw.learn.ext.HelloService;
import com.hzw.learn.sofa_base.server.HelloServiceImpl;

/**
 * @ClassName SofaRegistryServer
 * @Description TODO
 * @Author houzw
 * @Date 2022/5/27
 **/
public class SofaRegistryServer {

    public static void main(String[] args) {

        RegistryConfig registryConfig = new RegistryConfig()
                .setProtocol(RpcConstants.REGISTRY_PROTOCOL_SOFA)
                .setProtocol("nacos")
                .setAddress("127.0.0.1:8848/test");

        ServerConfig serverConfig = new ServerConfig()
                .setProtocol("bolt")
                .setPort(12200)
                .setDaemon(false);

        ProviderConfig<HelloService> providerConfig = new ProviderConfig<HelloService>()
                .setRegistry(registryConfig)
                .setInterfaceId(HelloService.class.getName())
                .setRef(new HelloServiceImpl())
                .setServer(serverConfig);

        providerConfig.export();
    }
}
