package com.hzw.learn.springboot.springbase.OrderOfMethod;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;


@Service
public class HzwBean2 implements InitializingBean {

    public HzwBean2(){
        System.out.println("构造方法2");
    }

    public void initMethod(){
        System.out.println("init方法2");
    }

    @PostConstruct
    public void postConstractMethod(){
        System.out.println("postConstract方法2");
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        System.out.println("afterPropertiesSet2");
    }
}
