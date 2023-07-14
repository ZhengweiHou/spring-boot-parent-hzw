package com.hzw.learn.springboot.springbase.DIAndIOC.DIAutowire;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.Arrays;

/**
 * @ClassName ParentTestBean
 * @Description TODO
 * @Author houzw
 * @Date 2023/5/15
 **/


public abstract class ParentTestBean {
//    @Autowired
    @Resource
    private ABean aBean;

    public void sayHelloParent(){
        System.out.println("parent hello name:" + aBean.getName());
    }
}
