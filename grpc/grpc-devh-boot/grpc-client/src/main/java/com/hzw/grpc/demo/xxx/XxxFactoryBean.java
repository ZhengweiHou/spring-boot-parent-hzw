package com.hzw.grpc.demo.xxx;

import org.springframework.beans.factory.FactoryBean;

import java.util.ArrayList;

/**
 * @ClassName XxxFactoryBean
 * @Description TODO
 * @Author houzw
 * @Date 2023/8/22
 **/
public class XxxFactoryBean implements FactoryBean {

    public XxxBeanDependencyBean xxxBeanDependencyBean;

    @Override
    public Object getObject() throws Exception {
        XxxBean xb = new XxxBean();
        xb.xxxBeanDependencyBean = xxxBeanDependencyBean;
        return xb;
    }

    @Override
    public Class<?> getObjectType() {
        return XxxBean.class;
    }

    public void setXxxBeanDependencyBean(XxxBeanDependencyBean xxxBeanDependencyBean) {
        this.xxxBeanDependencyBean = xxxBeanDependencyBean;
    }
}
