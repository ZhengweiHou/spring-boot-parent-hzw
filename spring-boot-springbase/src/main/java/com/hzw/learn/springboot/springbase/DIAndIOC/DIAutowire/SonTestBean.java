package com.hzw.learn.springboot.springbase.DIAndIOC.DIAutowire;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * @ClassName SonTestBean
 * @Description TODO
 * @Author houzw
 * @Date 2023/5/15
 **/
@Component
public class SonTestBean extends ParentTestBean{
//    @Autowired
    @Resource
    private ABean aBean1;

    public void sayHelloSon(){
        System.out.println("son hello name:" + aBean1.getName());
    }
}
