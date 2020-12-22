package com.hzw.learn.springboot.springbase.DIAndIOC.DICircle;

import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
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

//    @Test
//    public void helloTest_XML(){
//        applicationContext = new ClassPathXmlApplicationContext("DI/DICircle/springCircle.xml");
//    }

    @Test
    public void helloTest_JAVA(){
        applicationContext = new AnnotationConfigApplicationContext(SpringCircleConfig.class);
    }

}
