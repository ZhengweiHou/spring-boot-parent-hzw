package com.hzw.learn.springboot.springbase.DI.DICircle;

import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @ClassName CircleCBean
 * @Description TODO
 * @Author houzw
 * @Date 2020/7/3
 **/
@Component
public class CircleCBean implements InitializingBean, DisposableBean {
    @Autowired
    private CircleABean aBean;

    public CircleCBean() {
    }

    public CircleCBean(CircleABean aBean) {
        System.out.println(this.getClass().getSimpleName() + " 通过构造器注入" + CircleABean.class.getSimpleName());
        this.aBean = aBean;
    }

    public CircleABean getaBean() {
        return aBean;
    }

    public void setaBean(CircleABean aBean) {
        System.out.println(this.getClass().getSimpleName() + " 通过setter注入" + CircleABean.class.getSimpleName());
        this.aBean = aBean;
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
