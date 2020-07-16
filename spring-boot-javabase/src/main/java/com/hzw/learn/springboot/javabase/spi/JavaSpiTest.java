package com.hzw.learn.springboot.javabase.spi;

import org.junit.Test;

import java.util.ServiceLoader;

/**
 * @ClassName JavaSpiTest
 * @Description TODO
 * @Author houzw
 * @Date 2020/7/16
 **/
public class JavaSpiTest {
    @Test
    public void spiTest(){
        ServiceLoader<HelloSpiApi> load = ServiceLoader.load(HelloSpiApi.class);
        load.forEach(HelloSpiApi::sayHello);


    }
}
