package com.hzw.learn.springboot.springbase.Test;

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

public class Test {
    public static void main(String[] args) {
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
}
