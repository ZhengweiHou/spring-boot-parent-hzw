package com.hzw.learn.aop.aspectj;

import com.hzw.learn.aop.aspectj.dep.HzwPointAnno;
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
