package com.hzw.learn.sofa_base.client;

import com.alipay.sofa.rpc.config.ConsumerConfig;
import com.alipay.sofa.rpc.config.RegistryConfig;
import com.alipay.sofa.rpc.core.request.SofaRequest;
import com.alipay.sofa.rpc.registry.Registry;
import com.alipay.sofa.rpc.registry.RegistryFactory;
import com.hzw.learn.ext.HelloService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;

import java.lang.management.ManagementFactory;
import java.lang.management.RuntimeMXBean;
import java.util.List;

@Configuration
public class Client1Config {

    Logger logger = LoggerFactory.getLogger(Client1Config.class);

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
        return registryConfig;
    }

    @Bean
    public HelloService helloServiceRefer(){
        ConsumerConfig<HelloService> consumerConfig = new ConsumerConfig<>();
        consumerConfig.setId("helloconsumer_sofa2");
        consumerConfig.setProtocol("bolt"); // 默认bolt协议
        consumerConfig.setSerialization("protostuff"); // 默认hessian2
//        consumerConfig.setSerialization("jdk");
//        consumerConfig.setLoadBalancer();
        // consumerConfig.setProtocol("h2c");
        /* 代理的实现方式 可选SPI 如下，默认为javassist：
        jdk=com.alipay.sofa.rpc.proxy.jdk.JDKProxy
        javassist=com.alipay.sofa.rpc.proxy.javassist.JavassistProxy
        bytebuddy=com.alipay.sofa.rpc.proxy.bytebuddy.BytebuddyProxy*/
        consumerConfig.setProxy("jdk");

        /** TODO 此处添加的ProviderInfoListener无效 {@link com.alipay.sofa.rpc.bootstrap.DefaultConsumerBootstrap#refer} 处会重新覆盖ProviderInfoListener，
         那自定义的ProviderInfoListener如何使用呢 **/
//        consumerConfig.setProviderInfoListener(Sofa2ProviderInfoListener());
        consumerConfig.setRegistry(registryConfig());
        consumerConfig.setInterfaceId(HelloService.class.getName());
        consumerConfig.setProviderInfoListener(Sofa2ProviderInfoListener());
//        consumerConfig.setUniqueId("1");

        /** consumer 的粘滞设置，影响调用时的负载，关键处理代码：{@link com.alipay.sofa.rpc.client.AbstractCluster#select(SofaRequest, List)}*/
        consumerConfig.setSticky(false);
        consumerConfig.setCheck(false);
        consumerConfig.setTimeout(60000);
        HelloService hello = consumerConfig.refer();
        return hello;
//        return new HelloServiceImpl(); // MOCK
    }

    @Bean
    public void subscribeCustmerProviderInfoListener(){
        ConsumerConfig<HelloService> consumer = new ConsumerConfig();
        consumer.setInterfaceId(HelloService.class.getName());
        consumer.setProviderInfoListener(Sofa2ProviderInfoListener());
        Registry registry = RegistryFactory.getRegistry(registryConfig());

        if (!registry.start())
            registry.init();

        registry.subscribe(consumer);
//        List<ProviderGroup> aa = registry.subscribe(consumer);
//        logger.debug(new Gson().toJson(aa));
    }

    @Bean
//    @Scope("prototype")
    public HzwProviderInfoListener Sofa2ProviderInfoListener(){
        logger.info("creat ProviderInfoListener");
        return new HzwProviderInfoListener();
    }

//    @Bean
//    public PushReceiver pushReceiver(){
//        return new PushReceiver();
//    }





}
