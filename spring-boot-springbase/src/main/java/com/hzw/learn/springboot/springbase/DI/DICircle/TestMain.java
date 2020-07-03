package com.hzw.learn.springboot.springbase.DI.DICircle;

import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @ClassName TestMain
 * @Description TODO
 * @Author houzw
 * @Date 2020/7/1
 **/

@FixMethodOrder(MethodSorters.JVM)
public class TestMain {
    ApplicationContext applicationContext = null;
    @Before
    public void init(){
        applicationContext = new ClassPathXmlApplicationContext("DI/DICircle/springCircle.xml");
    }

    @Test
    public void helloTest(){

    }

}
