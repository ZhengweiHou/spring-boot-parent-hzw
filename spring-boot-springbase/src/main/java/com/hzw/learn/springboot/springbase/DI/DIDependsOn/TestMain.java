package com.hzw.learn.springboot.springbase.DI.DIDependsOn;

import com.google.gson.Gson;
import com.hzw.learn.springboot.springbase.DI.DIHello.HelloDIapi;
import com.hzw.learn.springboot.springbase.DI.DIHello.ListSetMapDITestBean;
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
        applicationContext = new ClassPathXmlApplicationContext("DI/DIDependsOn/springDependsOn.xml");
    }

    @Test
    public void helloTest(){

    }

}
