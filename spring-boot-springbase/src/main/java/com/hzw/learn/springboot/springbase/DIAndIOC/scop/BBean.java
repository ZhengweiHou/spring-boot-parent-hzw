package com.hzw.learn.springboot.springbase.DIAndIOC.scop;

import com.hzw.learn.springboot.springbase.DIAndIOC.DICircle.CircleCBean;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @ClassName BBean
 * @Description TODO
 * @Author houzw
 * @Date 2020/7/3
 **/
@Component
public class BBean implements InitializingBean, DisposableBean {

    public BBean() {
    }

    @Override
    public void destroy() throws Exception {
//        System.out.println(this.hashCode() + "=" + this.getClass().getSimpleName() + ":destroy!!");
    }

    @Override
    public void afterPropertiesSet() throws Exception {
//        System.out.println(this.hashCode() + "=" + this.getClass().getSimpleName() + ":afterPropertiesSet!!");

    }

    public void sayHello(){
        System.out.println("hello I'm " + this);
    }

    public BBeanLookup createBBean() {
        return null;
    }
}
