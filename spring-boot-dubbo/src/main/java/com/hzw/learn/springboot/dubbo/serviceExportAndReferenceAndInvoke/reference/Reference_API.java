package com.hzw.learn.springboot.dubbo.serviceExportAndReferenceAndInvoke.reference;

import com.hzw.learn.springboot.dubbo.serviceExportAndReferenceAndInvoke.api.Hi;
import org.apache.dubbo.common.URL;
import org.apache.dubbo.config.ApplicationConfig;
import org.apache.dubbo.config.ReferenceConfig;
import org.apache.dubbo.config.RegistryConfig;
import org.apache.dubbo.registry.Registry;
import org.apache.dubbo.registry.integration.RegistryProtocol;
import org.apache.dubbo.remoting.exchange.ExchangeClient;
import org.apache.dubbo.rpc.Invoker;
import org.apache.dubbo.rpc.cluster.Cluster;
import org.apache.dubbo.rpc.cluster.Directory;
import org.apache.dubbo.rpc.cluster.directory.StaticDirectory;
import org.apache.dubbo.rpc.cluster.support.FailoverCluster;
import org.apache.dubbo.rpc.cluster.support.FailoverClusterInvoker;
import org.apache.dubbo.rpc.cluster.support.RegistryAwareCluster;
import org.apache.dubbo.rpc.protocol.AbstractProtocol;
import org.apache.dubbo.rpc.protocol.dubbo.DubboInvoker;
import org.apache.dubbo.rpc.protocol.dubbo.DubboProtocol;
import org.apache.dubbo.rpc.protocol.injvm.InjvmProtocol;
import org.apache.dubbo.rpc.proxy.javassist.JavassistProxyFactory;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Map;
import java.util.Set;

public class Reference_API {

    @Test
    public void test1() throws InterruptedException {
    	ApplicationConfig application = new ApplicationConfig("consumer-api");
    	
    	RegistryConfig registry_zookeeper = new RegistryConfig("zookeeper://127.0.0.1:2181");

//        registry_zookeeper.setProtocol("http"); // 指定注册协议，没指定则默认是dubbo

        ArrayList<RegistryConfig> registries = new ArrayList<>();
        registries.add(registry_zookeeper);

        ReferenceConfig<Hi> reference = new ReferenceConfig<>();
        reference.setApplication(application);
        reference.setRegistries(registries);
//        reference.setGroup("*");
        reference.setInterface(Hi.class);
        reference.setVersion("*");
        reference.setInit(true);        // true:饿汉模式；false:懒汉模式
        reference.setRetries(2);

    	Hi hi3 = reference.get();

//        hi3.sayhi("Consumer_Api");
        while(true) {
            System.out.println(hi3.sayhi("Consumer_Api"));
        	Thread.sleep(1000);
        }
    }

    {
//        DubboProtocol
//        AbstractProtocol
//        RegistryProtocol
//        DubboInvoker
//        Cluster
//        StaticDirectory
//        FailoverCluster
//        JavassistProxyFactory
//        InjvmProtocol
//        RegistryAwareCluster
//        FailoverCluster
//        FailoverClusterInvoker

    }
    /**
      服务引入过程
      @see ReferenceConfig#get()
      @see ReferenceConfig#init()
      @see ReferenceConfig#createProxy(Map)    Map:reference参数
      ├─ injvm
      └─ uninjvm（继续）
         ├─ map -> URL 由map为原材料构建出URL
         ├─ urls -> invokers   (若有多个url会将产出invokers 加入到 {@link Cluster#join(Directory)},Directory: new StaticDirectory(invokers),Cluster:like {@link FailoverCluster#join(Directory)})
         │  ├─ a. dubbo 协议直连目标服务 {@link DubboProtocol#refer(Class, URL)} realy by parent {@link AbstractProtocol#refer(Class, URL)}
            │  └─ {@link DubboProtocol#protocolBindingRefer(Class, URL)}
            │     └─ new {@link DubboInvoker#DubboInvoker(Class, URL, ExchangeClient[], Set)}  ExchangeClient[] get by {@link DubboProtocol#getClients(URL)}
            │        ├─ x. url.connections = 0  ExchangeClient[] get by {@link DubboProtocol#getSharedClient(URL, int shareconnections)}  FIXME 获取共享客户端
            │        └─ y. url.connections ！= 0 xchangeClient[] get by {@link DubboProtocol#initClient(URL)}                             FIXME 初始化新客户端
            └─ b. 通过注册中心 {@link RegistryProtocol#refer(Class, URL)}

     */

    /**
     服务引入过程
     @see ReferenceConfig#get()
     @see ReferenceConfig#init()
     @see ReferenceConfig#createProxy(Map)    Map:reference参数
        ├─ get {@link org.apache.dubbo.rpc.Invoker} from urls
        │   ├─ injvm
        │   │   └─ 本地引用  {@link InjvmProtocol#refer(Class, URL)}    return AsyncToSyncInvoker wrap Invoker
                    {@link InjvmProtocol#protocolBindin fasd  gRefer(Class, URL)}      new InjvmInvoker
        │   └─ uninjvm
        │       ├─ Map + url|registoryUrls --> urls
        │       │    === urls --> invokers ===
        │       ├─ a.服务直连  {@link DubboProtocol#refer(Class, URL)}    return AsyncToSyncInvoker wrap Invoker
        │       │   └─ {@link DubboProtocol#protocolBindingRefer(Class, URL)}
        │       │       └─ {@link DubboInvoker#DubboInvoker(Class, URL, ExchangeClient[], Set)}     new DubboInvoker
        │       │           ├─ x. url.connections = 0  ExchangeClient[] get by {@link DubboProtocol#getSharedClient(URL, int shareconnections)}  FIXME 获取共享客户端
        │       │           └─ y. url.connections ！= 0 xchangeClient[] get by {@link DubboProtocol#initClient(URL)}                             FIXME 初始化新客户端
        │       ├─ b.通过注册中心 {@link RegistryProtocol#refer(Class, URL)}      get Registry
        │       │   └─ {@link RegistryProtocol#doRefer(Cluster, Registry, Class, URL)}
        │       │       └─ {@link FailoverCluster#join(Directory)}  new RegistryDirectory   FIXME 这里配置了group的话就是MergeableCluster
        │       │           └─ {@link FailoverClusterInvoker#FailoverClusterInvoker(Directory)} new FailoverClusterInvoker
        │       │    === invokers -->  invoker===
        │       ├─ x. have registryUrl     {@link RegistryAwareCluster#join(Directory)}   new StaticDirectory(regUrl,invokers)        return Invoker
        │       └─ y. haven't registryUrl  {@link FailoverCluster#join(Directory)}        new StaticDirectory(invokers)               return Invoker
        ├─ FIXME 用元数据报告服务（metadataReportService），发布消费者信息
        └─ {@link org.apache.dubbo.rpc.proxy.javassist.JavassistProxyFactory#getProxy(Invoker)} return Proxy

     */

}
