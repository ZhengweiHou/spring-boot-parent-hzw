package com.hzw.learn.springboot.batchbase.scoperrtest;

import org.springframework.beans.factory.FactoryBean;
import org.springframework.beans.factory.config.AbstractFactoryBean;

/**
 * @ClassName HzwFactoryBean
 * @Description TODO
 * @Author houzw
 * @Date 2024/6/4
 **/
public class HzwFactoryBean2 extends AbstractFactoryBean<String> {
    @Override
    public Class<?> getObjectType() {
        return String.class;
    }

    @Override
    protected String createInstance() throws Exception {
        return "hzw2";
    }
}
