package com.hzw.learn.springboot.dubbo.SPIdemo;

import org.apache.dubbo.common.Extension;

/**
 * @ClassName HelloSiriusExtensionImpl
 * @Description TODO
 * @Author houzw
 * @Date 2020/7/15
 **/
@Extension("siriusByExtension")
public class HelloSiriusExtensionImpl implements HelloApi {
    @Override
    public void sayHello() {
        System.out.println("hello I'm SiriusExtension:" + this.hashCode());
    }
}
