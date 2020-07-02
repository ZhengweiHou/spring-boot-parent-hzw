package com.hzw.learn.springboot.springbase.Autowire;

import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @ClassName TestMain
 * @Description TODO
 * @Author houzw
 * @Date 2020/7/2
 **/

@FixMethodOrder(MethodSorters.JVM)
public class TestMain {
    ApplicationContext applicationContext = null;
    @Before
    public void init(){
        applicationContext = new ClassPathXmlApplicationContext("Autowire/springAutowire.xml");
    }

    @Test
    public void helloTest(){
        //        使用注解方式进行autowried注入
        AnnotationAutowiredTestBean annotationAutowiredTestBean = applicationContext.getBean(AnnotationAutowiredTestBean.class);
        annotationAutowiredTestBean.sayHello();


        XmlAutowireTestBean xmlAutowireTestBean = applicationContext.getBean(XmlAutowireTestBean.class);
        xmlAutowireTestBean.sayHello();

//        com.hzw.learn.springboot.springbase.Autowire.AnnotationAutowiredTestBean的setAnnotationABean方法执行
//        com.hzw.learn.springboot.springbase.Autowire.XmlAutowireTestBean的setaBean1方法执行
//        com.hzw.learn.springboot.springbase.Autowire.XmlAutowireTestBean的setaBean2方法执行
//        aBean:sirius-abean1
//        annotationABean:sirius-abean2
//        aBean1:张三
//        aBean2:李四

    }

}
