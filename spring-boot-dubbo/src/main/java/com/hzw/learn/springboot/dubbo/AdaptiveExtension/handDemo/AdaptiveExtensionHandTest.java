package com.hzw.learn.springboot.dubbo.AdaptiveExtension.handDemo;

import org.apache.dubbo.common.URL;
import org.junit.Test;

/**
 * @ClassName AdaptiveExtensionHandTest
 * @Description 手写自适应扩展测试；该案例手写了自适应扩展的核心实现，用于直观了解自适应扩展到底做了什么。
 * @Author houzw
 * @Date 2020/7/16
 **/
public class AdaptiveExtensionHandTest {
    @Test
    public void test() {
        PencilMaker pencilMaker = new PencilMaker();
        pencilMaker.setRefillMaker(new AdaptiveRefillMaker());  // 模拟注入RefillMaker，这里注入自适应笔芯制造商

        URL url1 = URL.valueOf("hzw://xxx.xxx.xxx.xxx:20880/hzwService?refill.maker=redmaker");
        Pencil pencil1 = pencilMaker.makePencil(url1);
        pencil1.write();

        URL url2 = URL.valueOf("hzw://xxx.xxx.xxx.xxx:20880/hzwService?refill.maker=bluemaker");
        Pencil pencil2 = pencilMaker.makePencil(url2);
        pencil2.write();


    }
}
