package com.hzw.learn.springboot.springbase.DIAndIOC.scop;

import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

/**
 * @ClassName TestMain
 * @Description TODO
 * @Author houzw
 * @Date 2020/7/1
 **/

@FixMethodOrder(MethodSorters.JVM)
public class TestMain {

    @Test
    public void protoTypeConfigTest() throws ParseException {
        BeanFactory app = new AnnotationConfigApplicationContext(ScopTestConfiguration.class);
        System.out.println("==========Api配置原型模式的list内部定义Bean==========");
        ArrayList listBeans1 = app.getBean("listBeans", ArrayList.class);
        System.out.println(listBeans1);
        ArrayList listBeans2 = app.getBean("listBeans", ArrayList.class);
        System.out.println(listBeans2);
        System.out.println(((ABean)listBeans1.get(0)).getbBean());
        System.out.println(((ABean)listBeans2.get(0)).getbBean());

    }

    @Test
    public void protoTypeTest() throws ParseException {
        ClassPathXmlApplicationContext app = new ClassPathXmlApplicationContext("DIAndIOC/scop/prototype.xml");

        System.out.println("==========xml配置原型模式的aBean注入单例模式的bBean==========");
        BBean bBean = null;
        bBean = (BBean) app.getBean("bBean0");
        System.out.println("" + bBean.hashCode());
        bBean = (BBean)app.getBean("bBean0");
        System.out.println("" + bBean.hashCode());

        ABean aBean = (ABean) app.getBean("aBean0");
        System.out.println("a:" + aBean.hashCode() + " a.b:" + aBean.getbBean().hashCode());
        aBean = (ABean) app.getBean("aBean0");
        System.out.println("a:" + aBean.hashCode() + " a.b:" + aBean.getbBean().hashCode());


        System.out.println("==========xml配置原型模式的aBean注入原型模式的bBean==========");
        bBean = null;
        bBean = (BBean) app.getBean("bBean1");
        System.out.println("" + bBean.hashCode());
        bBean = (BBean)app.getBean("bBean1");
        System.out.println("" + bBean.hashCode());

        aBean = (ABean) app.getBean("aBean1");
        System.out.println("a:" + aBean.hashCode() + " a.b:" + aBean.getbBean().hashCode());
        aBean = (ABean) app.getBean("aBean1");
        System.out.println("a:" + aBean.hashCode() + " a.b:" + aBean.getbBean().hashCode());

        System.out.println("==========xml配置原型模式的list内部定义Bean==========");
        ArrayList listBeans1 = app.getBean("listBeans", ArrayList.class);
        System.out.println(listBeans1);
        ArrayList listBeans2 = app.getBean("listBeans", ArrayList.class);
        System.out.println(listBeans2);
        System.out.println(((ABean)listBeans1.get(0)).getbBean());
        System.out.println(((ABean)listBeans2.get(0)).getbBean());

        System.out.println("==========xml配置单例模式的list内部定义Bean==========");
        ArrayList listBeans_1 = app.getBean("listBeans_1", ArrayList.class);
        System.out.println(listBeans_1);
        ArrayList listBeans_2 = app.getBean("listBeans_2", ArrayList.class);
        System.out.println(listBeans_2);
        System.out.println(((ABean)listBeans_1.get(0)).getbBean());
        System.out.println(((ABean)listBeans_2.get(0)).getbBean());

    }

    @Test
    public void helloTest(){
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("DIAndIOC/scop/springScop.xml");
        ABean aBean1 = (ABean) applicationContext.getBean("aBean1");
        System.out.println("======" + aBean1.getbBean().hashCode());

        ABean aBean2 = (ABean) applicationContext.getBean("aBean2");
        System.out.println(aBean2.getbBean().hashCode());

        ABean aBean3 = (ABean) applicationContext.getBean("aBean3");
        System.out.println(aBean3.getbBean().hashCode());

        ABean aBean4 = (ABean) applicationContext.getBean("aBean4");
        System.out.println(aBean4.getbBean().hashCode());
        aBean4.getbBean().sayHello();

        BBeanLookup bBeanLookup = (BBeanLookup) applicationContext.getBean("bBeanLookup");
        bBeanLookup.createBBean().sayHello();

    }

}
