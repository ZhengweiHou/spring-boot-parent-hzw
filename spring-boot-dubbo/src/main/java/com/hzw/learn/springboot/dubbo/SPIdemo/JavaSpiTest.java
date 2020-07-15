package com.hzw.learn.springboot.dubbo.SPIdemo;

import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import java.util.ServiceLoader;

/**
 * @ClassName JavaSpiTest
 * @Description TODO
 * @Author houzw
 * @Date 2020/7/15
 **/
@FixMethodOrder(MethodSorters.JVM)
public class JavaSpiTest {

    @Test
    public void spiSayHello(){
        ServiceLoader<HelloApi> load = ServiceLoader.load(HelloApi.class);
        System.out.println("Java SPI");
        load.forEach(HelloApi::sayHello);
    }


}
