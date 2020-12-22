package com.hzw.learn.springboot.springbase.DIAndIOC.scop;

import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @ClassName ABean
 * @Description TODO
 * @Author houzw
 * @Date 2020/7/3
 **/
@Component
public class ABean implements InitializingBean, DisposableBean {
    @Autowired
    private BBean bBean;

    public ABean() {
    }

    public ABean(BBean bBean) {
        System.out.println(this.getClass().getSimpleName() + " 通过构造器注入" + bBean.getClass().getSimpleName());
        this.bBean = bBean;
    }

    public BBean getbBean() {
        return bBean;
    }

    public void setbBean(BBean bBean) {
        System.out.println(this.getClass().getSimpleName() + " 通过setter注入" + bBean.getClass().getSimpleName());
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
