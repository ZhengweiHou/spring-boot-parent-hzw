package com.hzw.learn.springboot.dubbo.AdaptiveExtension.dubboDemo;

import com.hzw.learn.springboot.dubbo.AdaptiveExtension.handDemo.Refill;
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
        Refill refill = new Refill("蓝");
        System.out.println("制造" + refill.getColor() + "色笔芯");
        return refill;
    }

    @Override
    public Refill makeRefillLight(URL url) {
        Refill refill = new Refill("淡蓝");
        System.out.println("制造" + refill.getColor() + "色笔芯");
        return refill;
    }
}
