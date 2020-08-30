package com.hzw.learn.springboot.springbase.DI.DIHello;

import com.google.gson.Gson;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.lang.reflect.Field;
import java.util.Arrays;

@FixMethodOrder(MethodSorters.JVM)
public class TestMain_JAVA {
    ApplicationContext applicationContext = null;
    @Before
    public void init(){
        applicationContext = new AnnotationConfigApplicationContext(SpringDiConfig.class);
    }

    /*
      常量注入
   */
    @Test
    public void helloTest(){
        // 根据 index、name、type 通过构造器注入参数
        HelloDIapi hellodi1_0= applicationContext.getBean("hellodi1", HelloDIapi.class);
        hellodi1_0.sayHello();

        //通过setter方法注入
        HelloDIapi hellodi2_0 = applicationContext.getBean("hellodi2_0", HelloDIapi.class);
        hellodi2_0.sayHello();

        // 注入bean id
        HelloDIapi hellodi3_0 = applicationContext.getBean("hellodi3_0", HelloDIapi.class);
        hellodi3_0.sayHello();
        HelloDIapi hellodi3_1 = applicationContext.getBean("hellodi3_1", HelloDIapi.class);
        hellodi3_1.sayHello();
    }

    /*
       集合注入 // FIXME 这就不演示了，java里操作这个简直太正常不过了；我们重点关注xml里怎么操作的
    */


    /*
      bean注入
   */
    @Test
    public void beanDITest(){
//        HelloDIapi beanDi1 = applicationContext.getBean("beanDi1", HelloDIapi.class);
//        beanDi1.sayHello();

        HelloDIapi beanDi2 = applicationContext.getBean("beanDi2", HelloDIapi.class);
        beanDi2.sayHello();

        HelloDIapi beanDi3 = applicationContext.getBean("beanDi3", HelloDIapi.class);
        beanDi3.sayHello();

    }
}
