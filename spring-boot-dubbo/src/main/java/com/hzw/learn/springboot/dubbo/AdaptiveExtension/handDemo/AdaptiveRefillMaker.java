package com.hzw.learn.springboot.dubbo.AdaptiveExtension.handDemo;

import org.apache.dubbo.common.URL;
import org.apache.dubbo.common.extension.ExtensionLoader;

/**
 * @ClassName AdaptiveRefillMaker
 * @Description 自适应笔芯制造商
 * @Author houzw
 * @Date 2020/7/16
 **/
public class AdaptiveRefillMaker implements RefillMaker{
    @Override
    public Refill makeRefill(URL url) {
        if(url == null){
            throw  new IllegalArgumentException("url is null");
        }

        String refillMakerName = url.getParameter("refill.maker");

//        根据调用者参数，通过spi，自适应调用者需要
        RefillMaker refillMaker = ExtensionLoader.getExtensionLoader(RefillMaker.class).getExtension(refillMakerName);

        return refillMaker.makeRefill(url);
    }
}
