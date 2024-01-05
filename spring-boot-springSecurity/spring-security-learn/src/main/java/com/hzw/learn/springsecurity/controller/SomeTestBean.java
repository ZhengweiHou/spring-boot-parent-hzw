package com.hzw.learn.springsecurity.controller;

import com.hzw.learn.springsecurity.dep.HzwPointAnno;
import org.springframework.stereotype.Component;

/**
 * @ClassName SomeTestBean
 * @Description TODO
 * @Author houzw
 * @Date 2023/10/11
 **/
@Component
public class SomeTestBean {

    @HzwPointAnno
    public String test1(){
        System.out.println(this.getClass().getSimpleName() + ".test1()");
        return "hello";
    }

}
