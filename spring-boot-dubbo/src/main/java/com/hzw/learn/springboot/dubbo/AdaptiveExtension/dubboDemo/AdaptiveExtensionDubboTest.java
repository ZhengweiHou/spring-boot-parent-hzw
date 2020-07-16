package com.hzw.learn.springboot.dubbo.AdaptiveExtension.dubboDemo;

import com.hzw.learn.springboot.dubbo.AdaptiveExtension.handDemo.RefillMaker;
import org.apache.dubbo.common.URL;
import org.apache.dubbo.common.extension.ExtensionLoader;
import org.junit.Test;

/**
 * @ClassName AdaptiveExtensionDubboTest
 * @Description 自适应扩展Dubbo实现
 * @Author houzw
 * @Date 2020/7/16
 **/
public class AdaptiveExtensionDubboTest {

    @Test
    public void test(){
        ExtensionLoader<RefillMaker> extensionLoader = ExtensionLoader.getExtensionLoader(RefillMaker.class);
        extensionLoader.getAdaptiveExtension();


    }
}
