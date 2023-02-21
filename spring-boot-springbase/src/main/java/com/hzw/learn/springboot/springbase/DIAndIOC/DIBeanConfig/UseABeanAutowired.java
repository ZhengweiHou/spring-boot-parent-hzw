package com.hzw.learn.springboot.springbase.DIAndIOC.DIBeanConfig;

import org.springframework.beans.factory.annotation.Autowired;

public class UseABeanAutowired {
    @Autowired
    public ABean aBean;

    public ABean getaBean() {
        return aBean;
    }

    public void setaBean(ABean aBean) {
        this.aBean = aBean;
    }
}
