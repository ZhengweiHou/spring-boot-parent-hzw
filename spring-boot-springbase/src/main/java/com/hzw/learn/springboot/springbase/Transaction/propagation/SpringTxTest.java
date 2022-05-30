package com.hzw.learn.springboot.springbase.Transaction.propagation;

import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.Map;

/**
 * @ClassName SpringTxTest
 * @Description TODO
 * @Author houzw
 * @Date 2022/4/25
 **/
@FixMethodOrder(MethodSorters.JVM)
public class SpringTxTest {

    IPropagationTestService propagationTest1Service;
    IPropagationTestService propagationTest2Service;
    PropagationSimpleTestService propagationSimpleTestService;

    @Before
    public void init() throws InterruptedException {
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("Transaction/Transaction.xml");
        this.propagationTest1Service = (IPropagationTestService) applicationContext.getBean("propagationTest1Service");
        this.propagationTest2Service = (IPropagationTestService) applicationContext.getBean("propagationTest2Service");
        this.propagationSimpleTestService = (PropagationSimpleTestService) applicationContext.getBean("propagationSimpleTestService");
        Thread.sleep(100);
    }


    @Test
    public void test1(){
        int i = propagationTest1Service.tx1();
    }

    @Test
    public void test2(){
        int i = propagationTest1Service.tx2();
    }

    @Test
    public void test3(){
        int i = propagationTest1Service.tx3();
    }

    @Test
    public void test4() throws InterruptedException {
        propagationSimpleTestService.case1();
        Thread.sleep(1000);
    }












}
