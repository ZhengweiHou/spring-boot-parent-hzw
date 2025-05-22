package com.hzw.learn.springboot.springbase.Transaction.isolation;

import com.hzw.learn.springboot.springbase.Transaction.propagation.IPropagationTestService;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @ClassName SpringTxTest
 * @Description TODO
 * @Author houzw
 * @Date 2022/4/25
 **/
@FixMethodOrder(MethodSorters.JVM)
public class SpringTxTest {

    IsolationSimpleTestService isolationSimpleTestService;

    @Before
    public void init() throws InterruptedException {
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("Transaction/Isolation.xml");
        this.isolationSimpleTestService = (IsolationSimpleTestService) applicationContext.getBean("isolationSimpleTestService");
        Thread.sleep(100);
    }


    @Test
    public void test1() throws Exception {
        isolationSimpleTestService.case1();
    }

    @Test
    public void test2() throws Exception {
        isolationSimpleTestService.case2();
    }














}
