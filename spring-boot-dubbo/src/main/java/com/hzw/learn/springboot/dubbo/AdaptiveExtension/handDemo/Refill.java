package com.hzw.learn.springboot.dubbo.AdaptiveExtension.handDemo;


/**
 * @ClassName Refill
 * @Description 笔芯接口
 * @Author houzw
 * @Date 2020/7/16
 **/
public class Refill {
    private String color;

    public Refill(String color) {
        this.color = color;
    }

    public String getColor() {
        return color;
    }
}
