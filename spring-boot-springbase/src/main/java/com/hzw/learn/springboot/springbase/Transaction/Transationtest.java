package com.hzw.learn.springboot.springbase.Transaction;

import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @ClassName Transationtest
 * @Description TODO
 * @Author houzw
 * @Date 2020/9/4
 **/
@FixMethodOrder(MethodSorters.JVM)
public class Transationtest {

    @Test
    public void helltest(){
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("Transaction/Transaction.xml");

        HzwService hzwService = (HzwService) applicationContext.getBean("hzwService");

//        hzwService.insertHZ();
//        hzwService.insertHZ_Exception2();
        hzwService.insertHZ_Exception2_but_try();
    }

}
