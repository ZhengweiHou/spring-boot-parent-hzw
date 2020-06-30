package com.hzw.learn.springboot.springbase.springbean;

import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.xml.XmlBeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

import javax.xml.crypto.dsig.keyinfo.KeyValue;
import java.awt.image.Kernel;
import java.util.Map;
import java.util.function.Consumer;

/**
 * @ClassName hellotest
 * @Description TODO
 * @Author houzw
 * @Date 2020/6/29
 **/
@FixMethodOrder(MethodSorters.JVM)
public class hellotest {

    @Test
    public void helltest(){

        // 主要继承实现关系
//        ClassPathXmlApplicationContext -> ...-> ApplicationContext -> ListableBeanFactory -> BeanFactory

//        // 1.准备配置文件，从当前类加载路径中获取配置文件
//        Resource resource = new ClassPathResource("springbean/springbean.xml");
//        // 2.初始化容器
//        BeanFactory beanFactory = new XmlBeanFactory(resource);

        // 获取配置文件，初始化容器
        BeanFactory beanFactory =
                new ClassPathXmlApplicationContext(
                        "springbean/springbean.xml");

        // xml 定义bean
        HelloApi hello1 = beanFactory.getBean("hello1", HelloApi.class);
        hello1.sayHello();

        // xml 定义别名
        System.out.print("通过alias获取bean： ");
        HelloApi helloalias1 = beanFactory.getBean("helloalias1", HelloApi.class);
        helloalias1.sayHello();

        // xml定义bean 单个构造参数
        HelloApi hello2 = beanFactory.getBean("hello2", HelloApi.class);
        hello2.sayHello();

        // xml定义bean 多构造参数
        HelloApi hello3 = beanFactory.getBean("hello3", HelloApi.class);
        hello3.sayHello();

        // 通过下标和name指定构造参数
        HelloApi hello4 = beanFactory.getBean("hello4", HelloApi.class);
        hello4.sayHello();

        // 使用静态工厂实例化
        HelloApi hello5 = beanFactory.getBean("hello5", HelloApi.class);
        hello5.sayHello();

        // 使用实例工厂实例化
        HelloApi hello6 = beanFactory.getBean("hello6", HelloApi.class);
        hello6.sayHello();

        System.out.println("================我是分割线================");
        // ApplicationContext 可以通过类型获取所有bean
        ApplicationContext beanFactory1 = (ApplicationContext) beanFactory;
        Map<String, HelloApi> beans = beanFactory1.getBeansOfType(HelloApi.class);
        beans.values().forEach(HelloApi::sayHello);

        // beanFaxtory 通过类型获取实例时，有多个时会异常
        HelloApi bean = beanFactory.getBean(HelloApi.class);
        bean.sayHello();


        // 这里留个疑问： 此类中的beanfactory是现场定义出来的，那在后续程序中肯定不能再初始化这个beanfactory，那时怎么获取这个beanfactory对象呢？
    }

}
