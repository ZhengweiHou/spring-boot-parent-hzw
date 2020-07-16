package com.hzw.learn.springboot.dubbo.AdaptiveExtension.handDemo;

/**
 * @ClassName Pencil
 * @Description TODO
 * @Author houzw
 * @Date 2020/7/16
 **/
public class Pencil {
    private Refill refill;

    public Pencil(Refill refill) {
        this.refill = refill;
    }

    public void write(){
        System.out.println("笔写出了字，颜色是：" + refill.getColor() );
    }
}
