package com.hzw.learn.springboot.springbase.Test;

import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.AnnotatedBeanDefinition;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.config.BeanDefinitionHolder;
import org.springframework.beans.factory.config.BeanExpressionResolver;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotatedBeanDefinitionReader;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ClassPathBeanDefinitionScanner;
import org.springframework.context.support.ClassPathXmlApplicationContext;

@FixMethodOrder(MethodSorters.JVM)

public class Test1 {

    @Test
    public void test1() {
        BeanDefinitionRegistry beanDefinitionRegistry;
        ApplicationContext applicationContext;
        AnnotationConfigApplicationContext annotationConfigApplicationContext;

        DefaultListableBeanFactory defaultListableBeanFactory;
//        beandefinitionNames
        AnnotatedBeanDefinitionReader annotatedBeanDefinitionReader;
//        BeanDefinitionMap
        BeanExpressionResolver beanExpressionResolver;
        BeanPostProcessor beanPostProcessor;
        BeanDefinitionHolder beanDefinitionHolder;
        ClassPathBeanDefinitionScanner classPathBeanDefinitionScanner;

        BeanDefinition beanDefinition;
        AnnotatedBeanDefinition annotatedBeanDefinition;
    }

    @Test
    public void test2() throws InterruptedException {
        ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext("Test/Test.xml");
//        PenBean penBean = (PenBean) applicationContext.getBean("penBean");

//        new Thread(new Runnable() {
//            @Override
//            public void run() {
//                System.out.println("秀才等书童送笔过来...");
//                while (0 == penBean.pen){}
//                System.out.println("书童笔送来了，开始作诗...");
//            }
//        },"秀才").start();

        PenBean penBean = (PenBean) applicationContext.getBean("penBean");
        System.out.println("-----" + penBean.pen);
        new Thread(new Runnable() {
            @Override
            public void run() {
                PenBean penBean = (PenBean) applicationContext.getBean("penBean");
                penBean.pen++;
            }
        },"书童").start();



        Thread.sleep(1000);

        new Thread(new Runnable() {
            @Override
            public void run() {

                PenBean penBean = (PenBean) applicationContext.getBean("penBean");
                System.out.println("=======" + penBean.pen);
            }
        },"show pen").start();

    }








}
