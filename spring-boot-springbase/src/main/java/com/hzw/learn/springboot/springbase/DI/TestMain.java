package com.hzw.learn.springboot.springbase.DI;

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

    @Test
    public void hellotest(){
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("DI/springDI.xml");

        // 根据 index、name、type 注入参数
        HelloDIapi hellodi1_0= applicationContext.getBean("hellodi1-index", HelloDIapi.class);
        hellodi1_0.sayHello();
        HelloDIapi hellodi1_1 = applicationContext.getBean("hellodi1-name", HelloDIapi.class);
        hellodi1_1.sayHello();
        HelloDIapi hellodi1_2 = applicationContext.getBean("hellodi1-type", HelloDIapi.class);
        hellodi1_2.sayHello();


        HelloDIapi hellodi2_0 = applicationContext.getBean("hellodi2_0", HelloDIapi.class);
        hellodi2_0.sayHello();
        HelloDIapi hellodi2_1 = applicationContext.getBean("hellodi2_1", HelloDIapi.class);
        hellodi2_1.sayHello();

        HelloDIapi hellodi3_0 = applicationContext.getBean("hellodi3_0", HelloDIapi.class);
        hellodi3_0.sayHello();
        HelloDIapi hellodi3_1 = applicationContext.getBean("hellodi3_1", HelloDIapi.class);
        hellodi3_1.sayHello();
    }

}
