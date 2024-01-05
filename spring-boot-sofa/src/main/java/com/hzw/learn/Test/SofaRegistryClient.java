
package com.hzw.learn.Test;

import com.alipay.sofa.rpc.config.ConsumerConfig;
import com.alipay.sofa.rpc.config.RegistryConfig;
import com.alipay.sofa.rpc.context.RpcRuntimeContext;
import com.hzw.learn.ext.HelloService;
import com.hzw.learn.ext.W;

import java.lang.management.ManagementFactory;
import java.lang.management.RuntimeMXBean;

/**
 * @ClassName SofaRegistryServer
 * @Description TODO
 * @Author houzw
 * @Date 2022/5/27
 **/
public class SofaRegistryClient {
    public static void main(String[] args) {

        String nacosaddress = "127.0.0.1:8848/test";
//        String nacosaddress = "127.0.0.1:8148,127.0.0.1:8248,127.0.0.1:8348/test";
        if (args.length > 0){
            nacosaddress = args[0];
        }

        String processId = getProcessId();

        System.out.println("nacosaddress:"+nacosaddress+"  processId:"+processId+"  client strarting....");

        RegistryConfig registryConfig = new RegistryConfig()
//                .setProtocol(RpcConstants.REGISTRY_PROTOCOL_SOFA)
                .setProtocol("nacos")
//                .setAddress("127.0.0.1:8848/test");
                .setAddress(nacosaddress);

        ConsumerConfig<HelloService> consumerConfig = new ConsumerConfig<HelloService>()
                .setInterfaceId(HelloService.class.getName())
                .setRegistry(registryConfig)
                .setProtocol("bolt")
                .setConnectTimeout(3 * 1000);

        // 序列化类型
        consumerConfig.setSerialization("java");
        // 客户端动态代理方式
        consumerConfig.setProxy("jdk");

        HelloService helloService = consumerConfig.refer();
//        LOGGER.warn("started at pid {}", RpcRuntimeContext.PID);
        System.out.println("started at pid " + RpcRuntimeContext.PID);


        // Test 1
        W w = new W();
        w.msg = "hello w";
        W w2 = helloService.hellow(w);
        System.out.println(w2.msg);

        // Test 2
//        int times = 0;
//        try {
//            while (true) {
//                try {
//                    System.out.println(helloService.hello("I'm client:[" + processId +"]="+ ++times));
//                } catch (Exception e) {
//                    System.out.println(e.getMessage());
//                }
//                try {
//                    Thread.sleep(2000);
//                } catch (Exception e) {
//                }
//            }
//        } catch (Exception e) {
//            System.out.println(e.getMessage());
//        }
    }

    public static String getProcessId(){
        RuntimeMXBean runtimeMXBean = ManagementFactory.getRuntimeMXBean();
        System.out.println(runtimeMXBean.getName());
        return runtimeMXBean.getName().split("@")[0];
    }

}