package com.hzw.learn.springboot.springbase.IOC.IocLifecycle;

import com.hzw.learn.springboot.springbase.IOC.hello.HelloApi;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.jdbc.core.metadata.GenericCallMetaDataProvider;

import java.util.Map;


@FixMethodOrder(MethodSorters.JVM)
public class IocLifecycleTestMain {

    @Test
    public void test1(){
//        BeanFactory beanFactory = new ClassPathXmlApplicationContext("IOC/IocLifecycle/IocLifecycle.xml");
        ApplicationContext context = new ClassPathXmlApplicationContext("IOC/IocLifecycle/IocLifecycle.xml");



    }

}
