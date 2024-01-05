package com.hzw.learn.springsecurity.dep;

import org.springframework.stereotype.Component;

/**
 * @ClassName SomeTestBeanDep
 * @Description TODO
 * @Author houzw
 * @Date 2023/10/11
 **/
@Component
public class SomeTestBeanDep {

    @HzwPointAnno
    public String test1(){
        System.out.println(this.getClass().getSimpleName() + ".test1()");
        return "hello";
    }

}
