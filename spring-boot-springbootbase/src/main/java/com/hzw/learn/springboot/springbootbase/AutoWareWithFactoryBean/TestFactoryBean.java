package com.hzw.learn.springboot.springbootbase.AutoWareWithFactoryBean;

import org.springframework.beans.factory.FactoryBean;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @ClassName TestFactoryBean
 * @Description TODO
 * @Author houzw
 * @Date 2023/8/2
 **/
public class TestFactoryBean implements FactoryBean<String> {

    public String p1;

    @Autowired
    HzwBean hzwBean;

    @Override
    public String getObject() throws Exception {
        System.out.println(hzwBean.getName() + "-" + p1);
        return hzwBean.getName() + "-" + p1;
    }

    @Override
    public Class<?> getObjectType() {
        return String.class;
    }

    public String getP1() {
        return p1;
    }

    public void setP1(String p1) {
        this.p1 = p1;
    }
}
