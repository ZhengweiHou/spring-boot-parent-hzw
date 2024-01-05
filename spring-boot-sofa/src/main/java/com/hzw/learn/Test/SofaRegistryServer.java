package com.hzw.learn.Test;

import com.alipay.sofa.rpc.common.RpcConstants;
import com.alipay.sofa.rpc.config.ProviderConfig;
import com.alipay.sofa.rpc.config.RegistryConfig;
import com.alipay.sofa.rpc.config.ServerConfig;
import com.hzw.learn.ext.HelloService;
import com.hzw.learn.ext.NetworkAddressUtil;
import com.hzw.learn.sofa_base.server.HelloServiceImpl;

import java.lang.management.ManagementFactory;
import java.lang.management.RuntimeMXBean;
import java.util.Random;

/**
 * @ClassName SofaRegistryServer
 * @Description TODO
 * @Author houzw
 * @Date 2022/5/27
 **/
public class SofaRegistryServer {

    public static void main(String[] args) {

        String nacosaddress = "127.0.0.1:8848/test";
//        String nacosaddress = "127.0.0.1:8148,127.0.0.1:8248,127.0.0.1:8348/test";
        int serverPort = (int) (10000 + System.currentTimeMillis()%10000);

        if (args.length > 0){
            nacosaddress = args[0];
        }
        if (args.length > 1){
            serverPort = Integer.parseInt(args[1]);
        }

        String processId = getProcessId();
        String ipRange = System.getProperty("ipRange", "");
        NetworkAddressUtil.caculate(ipRange, null);
        String serverIp = NetworkAddressUtil.getLocalIP();


        System.out.println("nacosaddress:" + nacosaddress
                +"  pid:" + processId
                +"  ip:"+ serverIp
                +"  port:" + serverPort
                +"  server strarting....");

        RegistryConfig registryConfig = new RegistryConfig()
                .setProtocol(RpcConstants.REGISTRY_PROTOCOL_SOFA)
                .setProtocol("nacos")
//                .setAddress("127.0.0.1:8848/test");
                .setAddress(nacosaddress);

        ServerConfig serverConfig = new ServerConfig()
                .setProtocol("bolt")
                .setVirtualHost(serverIp)
                .setBoundHost(serverIp)
                .setPort(serverPort)
//                .setVirtualPort(35929)
                .setAdaptivePort(true);
//                .setDaemon(false);

        // 序列化类型
        serverConfig.setSerialization("java");

        HelloServiceImpl helloService = new HelloServiceImpl();
        helloService.jvmProcessID = processId;
        ProviderConfig<HelloService> providerConfig = new ProviderConfig<HelloService>()
                .setRegistry(registryConfig)
                .setInterfaceId(HelloService.class.getName())
                .setRef(helloService)
                .setServer(serverConfig);

        providerConfig.export();

        System.out.println("nacosaddress:" + nacosaddress
                +"  pid:" + processId
                +"  ip:"+ serverIp
                +"  port:" + serverPort
                +"  server strarted!!");
    }

    public static String getProcessId(){
        RuntimeMXBean runtimeMXBean = ManagementFactory.getRuntimeMXBean();
        System.out.println(runtimeMXBean.getName());
        return runtimeMXBean.getName().split("@")[0];
    }
}
