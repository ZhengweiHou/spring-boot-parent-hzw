package com.hzw.learn.springboot.batchbase.scoperrtest;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.HashMap;

public class TestMain_XML {
    @Test
    public void test_batch_scoperrtest() {
        ApplicationContext ctx = new ClassPathXmlApplicationContext("scoperrtest/scoperrtest.xml");

        HzwBean2 hb1 = (HzwBean2) ctx.getBean("hzw3");
        System.out.println(hb1.map1);

//        HashMap map1 = (HashMap) ctx.getBean("hzwmapping");
//        map1.forEach((k,v) -> {
//            System.out.println(k + ":" + v);
//        });
//
//        HashMap map2 = (HashMap) ctx.getBean("auditEntitys");
//        map2.forEach((k,v) -> {
//            System.out.println(k + ":" + v);
//        });


    }
}



