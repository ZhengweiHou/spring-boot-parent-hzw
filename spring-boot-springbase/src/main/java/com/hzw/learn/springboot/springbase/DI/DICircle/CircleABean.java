package com.hzw.learn.springboot.springbase.DI.DICircle;

import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @ClassName CircleABean
 * @Description TODO
 * @Author houzw
 * @Date 2020/7/3
 **/
@Component
public class CircleABean implements InitializingBean, DisposableBean {
    @Autowired
    private CircleBBean bBean;

    public CircleABean() {
    }

    public CircleABean(CircleBBean bBean) {
        System.out.println(this.getClass().getSimpleName() + " 通过构造器注入" + CircleBBean.class.getSimpleName());
        this.bBean = bBean;
    }

    public CircleBBean getbBean() {
        return bBean;
    }

    public void setbBean(CircleBBean bBean) {
        System.out.println(this.getClass().getSimpleName() + " 通过setter注入" + CircleBBean.class.getSimpleName());
        this.bBean = bBean;
    }

    @Override
    public void destroy() throws Exception {
        System.out.println(this.hashCode() + "=" + this.getClass().getSimpleName()+ ":destroy!!");
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        System.out.println(this.hashCode() + "=" + this.getClass().getSimpleName() + ":afterPropertiesSet!!");
    }
}
