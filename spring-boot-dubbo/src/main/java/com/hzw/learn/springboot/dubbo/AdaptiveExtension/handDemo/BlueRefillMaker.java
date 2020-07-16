package com.hzw.learn.springboot.dubbo.AdaptiveExtension.handDemo;

import org.apache.dubbo.common.URL;

/**
 * @ClassName BlueRefillMaker
 * @Description 蓝色笔芯制造
 * @Author houzw
 * @Date 2020/7/16
 **/
public class BlueRefillMaker implements RefillMaker{
    @Override
    public Refill makeRefill(URL url) {
        return new Refill("蓝色");
    }
}
