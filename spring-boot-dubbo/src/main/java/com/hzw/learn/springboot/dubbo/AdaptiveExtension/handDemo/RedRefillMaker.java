package com.hzw.learn.springboot.dubbo.AdaptiveExtension.handDemo;

import org.apache.dubbo.common.URL;

/**
 * @ClassName RedRefillMaker
 * @Description 红色笔芯制造商
 * @Author houzw
 * @Date 2020/7/16
 **/
public class RedRefillMaker implements RefillMaker{
    @Override
    public Refill makeRefill(URL url) {
        return new Refill("红色");
    }
}
