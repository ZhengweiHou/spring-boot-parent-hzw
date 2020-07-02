package com.hzw.learn.springboot.springbase.DI;

import com.google.gson.Gson;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.lang.reflect.Field;
import java.util.Arrays;

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
        applicationContext = new ClassPathXmlApplicationContext("DI/springDI.xml");
    }

    /*
        常量注入
     */
    @Test
    public void helloTest(){
        // 根据 index、name、type 通过构造器注入参数
        HelloDIapi hellodi1_0= applicationContext.getBean("hellodi1-index", HelloDIapi.class);
        hellodi1_0.sayHello();
        HelloDIapi hellodi1_1 = applicationContext.getBean("hellodi1-name", HelloDIapi.class);
        hellodi1_1.sayHello();
        HelloDIapi hellodi1_2 = applicationContext.getBean("hellodi1-type", HelloDIapi.class);
        hellodi1_2.sayHello();

        //通过setter方法注入
        HelloDIapi hellodi2_0 = applicationContext.getBean("hellodi2_0", HelloDIapi.class);
        hellodi2_0.sayHello();
        HelloDIapi hellodi2_1 = applicationContext.getBean("hellodi2_1", HelloDIapi.class);
        hellodi2_1.sayHello();
// 注入bean id
        HelloDIapi hellodi3_0 = applicationContext.getBean("hellodi3_0", HelloDIapi.class);
        hellodi3_0.sayHello();
        HelloDIapi hellodi3_1 = applicationContext.getBean("hellodi3_1", HelloDIapi.class);
        hellodi3_1.sayHello();
    }

    /*
        集合注入
     */
    @Test
    public void listSerMapDITest(){
        ListSetMapDITestBean listSetMapDiTest = applicationContext.getBean("listSetMapDiTest", ListSetMapDITestBean.class);

        Field[] fields = listSetMapDiTest.getClass().getDeclaredFields();
        Arrays.stream(fields).forEach(field ->{
            try {
                field.setAccessible(true);
                Object fieldvalue = field.get(listSetMapDiTest);
                System.out.print(field.getName() + ":");
                System.out.println(new Gson().toJson(fieldvalue));
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        });

    }

    /*
        bean注入
     */
    @Test
    public void beanDITest(){
        HelloDIapi beanDi1 = applicationContext.getBean("beanDi1", HelloDIapi.class);
        beanDi1.sayHello();

        HelloDIapi beanDi2 = applicationContext.getBean("beanDi2", HelloDIapi.class);
        beanDi2.sayHello();

        HelloDIapi beanDi3 = applicationContext.getBean("beanDi3", HelloDIapi.class);
        beanDi3.sayHello();

    }

}
