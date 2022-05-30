
package com.hzw.learn.Test;

import com.alipay.sofa.rpc.common.RpcConstants;
import com.alipay.sofa.rpc.config.ConsumerConfig;
import com.alipay.sofa.rpc.config.RegistryConfig;
import com.alipay.sofa.rpc.context.RpcRuntimeContext;
import com.alipay.sofa.rpc.log.Logger;
import com.alipay.sofa.rpc.log.LoggerFactory;
import com.hzw.learn.sofa1.HelloService;

/**
 * @ClassName SofaRegistryServer
 * @Description TODO
 * @Author houzw
 * @Date 2022/5/27
 **/
public class SofaRegistryClient {
    public static void main(String[] args) {

        System.out.println("strarting....");

        RegistryConfig registryConfig = new RegistryConfig()
//                .setProtocol(RpcConstants.REGISTRY_PROTOCOL_SOFA)
                .setProtocol("nacos")
                .setAddress("127.0.0.1:8848");

        ConsumerConfig<HelloService> consumerConfig = new ConsumerConfig<HelloService>()
                .setInterfaceId(HelloService.class.getName())
                .setRegistry(registryConfig)
                .setProtocol("bolt")
                .setConnectTimeout(3 * 1000);

        HelloService helloService = consumerConfig.refer();
//        LOGGER.warn("started at pid {}", RpcRuntimeContext.PID);
        System.out.println("started at pid " + RpcRuntimeContext.PID);

        try {
            while (true) {
                try {
                    System.out.println(helloService.hello("world"));
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                }
                try {
                    Thread.sleep(2000);
                } catch (Exception e) {
                }
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

}