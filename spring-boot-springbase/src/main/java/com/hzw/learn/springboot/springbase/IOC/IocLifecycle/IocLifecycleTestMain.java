package com.hzw.learn.springboot.springbase.IOC.IocLifecycle;

import com.hzw.learn.springboot.springbase.IOC.IocLifecycle.PostProcessor.HzwBeanFactoryPostProcessor;
import com.hzw.learn.springboot.springbase.IOC.IocLifecycle.PostProcessor.HzwBfppBdrpp;
import com.hzw.learn.springboot.springbase.IOC.hello.HelloApi;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.jdbc.core.metadata.GenericCallMetaDataProvider;

import java.util.Map;
import java.util.Scanner;


@FixMethodOrder(MethodSorters.JVM)
public class IocLifecycleTestMain {

    public static void main(String[] args) throws ClassNotFoundException {
        // 提前加载class到方法区，用于arthas等工具能提前trac指定类，以便后续分析
        Class.forName("org.springframework.context.support.ClassPathXmlApplicationContext");
        Class.forName("org.springframework.context.support.PostProcessorRegistrationDelegate");

//        org.springframework.context.support.AbstractApplicationContext refresh


        // 手动阻塞
//        Scanner sc = new Scanner(System.in);
//        System.out.println("输入回车继续：");
//        String name = sc.nextLine();

        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("IOC/IocLifecycle/IocLifecycle.xml");

    }

//    @Test
//    public void test1() throws ClassNotFoundException {
//        Class.forName("org.springframework.context.support.ClassPathXmlApplicationContext");
//
//        Scanner sc = new Scanner(System.in);
//        System.out.println("输入回车继续：");
//        String name = sc.nextLine();
//
////        BeanFactory beanFactory = new ClassPathXmlApplicationContext("IOC/IocLifecycle/IocLifecycle.xml");
//        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("IOC/IocLifecycle/IocLifecycle.xml");
////        context.addBeanFactoryPostProcessor(new HzwBfppBdrpp());
//
//    }

}
