package com.hzw.learn.springboot.springbase.DIAndIOC.DIDependsOn;

import org.springframework.beans.factory.FactoryBean;
import org.springframework.beans.factory.config.AbstractFactoryBean;

public class D_FactoryBean implements FactoryBean<String>
//        extends AbstractFactoryBean<String>
{
    Object obj;

    @Override
    public String getObject() throws Exception {
        System.out.println(this.hashCode() + "=" + this.getClass().getSimpleName() + ": init !!");
        return "D_Bean:" + System.currentTimeMillis();
    }

    @Override
    public Class<?> getObjectType() {
        return FactoryBean.class;
    }

//    @Override
//    protected String createInstance() throws Exception {
//        System.out.println(this.hashCode() + "=" + this.getClass().getSimpleName() + ": init !!");
//        return "D_Bean:" + System.currentTimeMillis();
//    }

    public void setObj(Object obj){
        this.obj = obj;
    }


}
