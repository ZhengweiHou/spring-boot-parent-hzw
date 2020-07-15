package com.hzw.learn.springboot.dubbo.SPIdemo;

import org.apache.dubbo.common.extension.ExtensionLoader;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import java.lang.reflect.InvocationTargetException;
import java.util.ServiceLoader;

/**
 * @ClassName DubboSpiTest
 * @Description DubboSpi测试
 * @Author houzw
 * @Date 2020/7/15
 **/
@FixMethodOrder(MethodSorters.JVM)
public class DubboSpiTest {
    /*
    Dubbo 并未使用 Java SPI，而是重新实现了一套功能更强的 SPI 机制。
    Dubbo SPI 的相关逻辑被封装在了 ExtensionLoader 类中，
    通过 ExtensionLoader，我们可以加载指定的实现类。
    Dubbo SPI 所需的配置文件需放置在 META-INF/dubbo 路径下
    */

//    另外在使用DubboSPI时，需要在接口上添加@SPI注解

    @Test
    public void spiSayHello(){

        ExtensionLoader<HelloApi> extensionLoader = ExtensionLoader.getExtensionLoader(HelloApi.class);

        System.out.println("Dubbo SPI");

        HelloApi hellohzw = extensionLoader.getExtension("hellohzw");
        hellohzw.sayHello();

        HelloApi bbb = extensionLoader.getExtension("bbb");
        bbb.sayHello();

        HelloApi hellosirius = extensionLoader.getExtension("hellosirius");
        hellosirius.sayHello();

        HelloApi siriusByExtension = extensionLoader.getExtension("siriusByExtension");
        siriusByExtension.sayHello();



    }



}
