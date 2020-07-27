package com.hzw.learn.springboot.springbase.DI.scop;

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
        applicationContext = new ClassPathXmlApplicationContext("DI/scop/springScop.xml");
    }

    @Test
    public void helloTest(){
        ABean aBean1 = (ABean) applicationContext.getBean("aBean1");
        System.out.println(aBean1.getbBean().hashCode());
        System.out.println(aBean1.getbBean().hashCode());

        ABean aBean2 = (ABean) applicationContext.getBean("aBean2");
        System.out.println(aBean2.getbBean().hashCode());
        System.out.println(aBean2.getbBean().hashCode());

        ABean aBean3 = (ABean) applicationContext.getBean("aBean3");
        System.out.println(aBean3.getbBean().hashCode());
        System.out.println(aBean3.getbBean().hashCode());

        ABean aBean4 = (ABean) applicationContext.getBean("aBean4");
        System.out.println(aBean4.getbBean().hashCode());
        System.out.println(aBean4.getbBean().hashCode());
        aBean4.getbBean().sayHello();

        BBeanLookup bBeanLookup = (BBeanLookup) applicationContext.getBean("bBeanLookup");
        bBeanLookup.createBBean().sayHello();

    }

}
