package com.hzw.learn.springboot.dubbo.serviceExportAndReferenceAndInvoke.export;

import com.hzw.learn.springboot.dubbo.serviceExportAndReferenceAndInvoke.api.Hi;
import com.hzw.learn.springboot.dubbo.serviceExportAndReferenceAndInvoke.api.HiImpl;
import org.apache.dubbo.common.URL;
import org.apache.dubbo.config.*;
import org.apache.dubbo.config.invoker.DelegateProviderMetaDataInvoker;
import org.apache.dubbo.registry.integration.RegistryProtocol;
import org.apache.dubbo.rpc.Invoker;
import org.apache.dubbo.rpc.protocol.injvm.InjvmProtocol;
import org.apache.dubbo.rpc.proxy.javassist.JavassistProxyFactory;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;

public class Export_API {

    @Test
    public void test1() throws Exception {

    	ApplicationConfig applicationConfig = new ApplicationConfig("provider_api");

    	RegistryConfig registry_zookeeper = new RegistryConfig("zookeeper://127.0.0.1:2181");

//        registry_zookeeper.setProtocol("http"); // 指定注册协议，没指定则默认是dubbo

    	registry_zookeeper.setGroup("dubbo");

        ArrayList<RegistryConfig> registries = new ArrayList<>();
        registries.add(registry_zookeeper);

        ProtocolConfig protocol = new ProtocolConfig("dubbo",20882); // 协议配置

        ServiceConfig<Hi> service = new ServiceConfig<>();
        service.setApplication(applicationConfig);
        service.setRegistries(registries);
        service.setProtocol(protocol);
        service.setInterface(Hi.class);
        service.setRef(new HiImpl("Exportor"));
//        service.setGroup("servicegroup1");
//        service.setVersion("1.0.0");

        service.export();


        System.out.println("dubbo service started");
        new CountDownLatch(1).await();



    }

    {
//        InjvmProtocol
//        JavassistProxyFactory
//        RegistryProtocol
    }

    /**
     服务导出过程
     @see ServiceConfig#export()
     @see ServiceConfig#doExport()  延迟加载则延迟执行该方法
     @see ServiceConfig#doExportUrls()
        {@link ServiceConfig#loadRegistries(boolean)}    FIXME registryURLs
            RegistryConfigs  =>  URLs:registryURLs
        @see ServiceConfig#doExportUrlsFor1Protocol(ProtocolConfig, List)  List:registryURLs
            ServiceConfig => map => URL:serviceUrl       FIXME serviceUrl
            -scope=remote    {@link ServiceConfig#exportLocal(URL)}
                {@link JavassistProxyFactory#getInvoker(Object ref, Class interfaceClass, URL serviceUrl)}       FIXME Invoker
                {@link InjvmProtocol#export(Invoker)}    FIXME Exporter
            -scope=local     ServiceConfig#exportRemote(registryURLs, url)
                registryUrl 中装入 servieUrl             FIXME registryUrl[export=serviceUrl]
                {@link JavassistProxyFactory#getInvoker(Object ref, Class interfaceClass, URL registryUrl)}      FIXME Invoker
                new {@link DelegateProviderMetaDataInvoker#DelegateProviderMetaDataInvoker(Invoker, ServiceConfig)}  FIXME DelegateProviderMetaDataInvoker wrapperInvoker
                {@link RegistryProtocol#export(Invoker wrapperInvoker)}     FIXME Exporter
                    {@link RegistryProtocol#getRegistryUrl(Invoker)}        FIXME registryUrl
                    {@link RegistryProtocol#getProviderUrl(Invoker)}        FIXME providerUrl
        
            exporters.add(exporter)

     */
}
