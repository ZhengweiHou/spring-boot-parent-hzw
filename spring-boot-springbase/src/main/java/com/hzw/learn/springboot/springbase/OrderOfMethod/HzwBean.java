package com.hzw.learn.springboot.springbase.OrderOfMethod;

import org.springframework.beans.factory.InitializingBean;

import javax.annotation.PostConstruct;

public class HzwBean implements InitializingBean {

    public HzwBean(){
        System.out.println("构造方法");
    }

    public void initMethod(){
        System.out.println("init方法");
    }

    @PostConstruct
    public void postConstractMethod(){
        System.out.println("postConstract方法");
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        System.out.println("afterPropertiesSet");
    }

//    构造方法
//    postConstract方法
//    afterPropertiesSet
//    init方法
}
