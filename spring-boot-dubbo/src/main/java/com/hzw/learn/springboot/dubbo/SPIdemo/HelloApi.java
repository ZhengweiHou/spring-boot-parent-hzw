package com.hzw.learn.springboot.dubbo.SPIdemo;

import org.apache.dubbo.common.extension.SPI;

//@SPI("hellosirius,hellohzw")   指定多个默认扩展名，会报如下异常
//More than 1 default extension name on extension com.hzw.learn.springboot.dubbo.SPIdemo.HelloApi: [hellosirius, hellohzw]
@SPI        // DubboSPI要求接口需要添加@SPI注解
public interface HelloApi {
    void sayHello();
}
