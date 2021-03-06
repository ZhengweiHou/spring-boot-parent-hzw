package com.hzw.learn.springboot.springbase.DIAndIOC.DICircle;

import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @ClassName CircleBBean
 * @Description TODO
 * @Author houzw
 * @Date 2020/7/3
 **/
//@Component
public class CircleBBean implements InitializingBean, DisposableBean {
//    @Autowired
    private CircleCBean cBean;

    public CircleBBean() {
    }

    public CircleBBean(CircleCBean cBean) {
        System.out.println(this.getClass().getSimpleName() + " 通过构造器注入" + CircleCBean.class.getSimpleName());
        this.cBean = cBean;
    }

    public CircleCBean getcBean() {
        return cBean;
    }

    public void setcBean(CircleCBean cBean) {
        System.out.println(this.getClass().getSimpleName() + " 通过setter注入" + CircleCBean.class.getSimpleName());
        this.cBean = cBean;
    }

    @Override
    public void destroy() throws Exception {
        System.out.println(this.hashCode() + "=" + this.getClass().getSimpleName() + ":destroy!!");
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        System.out.println(this.hashCode() + "=" + this.getClass().getSimpleName() + ":afterPropertiesSet!!");

    }
}
