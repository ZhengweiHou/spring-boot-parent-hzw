package com.hzw.learn.springboot.springbootbase.AutoWareWithFactoryBean;

import jdk.nashorn.internal.objects.annotations.Getter;

/**
 * @ClassName HzwBean
 * @Description TODO
 * @Author houzw
 * @Date 2023/8/2
 **/
public class HzwBean {
    private String name;

    public HzwBean(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
