package com.hzw.learn.springboot.springbase.DIAndIOC.DIBeanConfig;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class TestMain {

    public static void main(String[] args) {
        ApplicationContext applicationContext = new AnnotationConfigApplicationContext(BeanConfiguration.class);

        UseABeanAutowired user1 = applicationContext.getBean("useABeanAutowired", UseABeanAutowired.class);
        UseABeanSimple user2 = applicationContext.getBean("useABeanSimple1", UseABeanSimple.class);
        UseABeanSimple user3 = applicationContext.getBean("useABeanSimple2", UseABeanSimple.class);

        System.out.println(user1.getaBean());
        System.out.println(user2.getaBean());
        System.out.println(user3.getaBean());

        if (user1.aBean == user2.getaBean()){
            if (user2.getaBean() == user3.getaBean()){
                System.out.println("aBean只实例化了一次，三种方式注入的aBean是同一个");
            }
        }

    }


}
