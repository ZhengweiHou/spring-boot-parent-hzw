package com.hzw.learn.springboot.dubbo.AdaptiveExtension.handDemo;

import org.apache.dubbo.common.URL;

/**
 * @ClassName PencilMaker
 * @Description TODO
 * @Author houzw
 * @Date 2020/7/16
 **/
public class PencilMaker {
    RefillMaker refillMaker;

    // setter 方法，用于注入RefillMaker
    public void setRefillMaker(RefillMaker refillMaker) {
        this.refillMaker = refillMaker;
    }

    public Pencil makePencil(URL url){
        Refill refill = refillMaker.makeRefill(url);
        return new Pencil(refill);
    }
}
