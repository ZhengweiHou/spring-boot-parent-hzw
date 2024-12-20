package com.hzw.learn.springboot.batchbase.scoperrtest;

import org.springframework.beans.factory.FactoryBean;

/**
 * @ClassName HzwFactoryBean
 * @Description TODO
 * @Author houzw
 * @Date 2024/6/4
 **/
public class HzwFactoryBean implements FactoryBean<String> {
    @Override
    public String getObject() throws Exception {
        return "hzw";
    }

    @Override
    public Class<?> getObjectType() {
        return String.class;
    }
}
