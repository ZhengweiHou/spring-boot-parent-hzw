package com.hzw.learn.springboot.springbase.springbean.hello;

import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import javax.sound.midi.VoiceStatus;

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
      // classpathxmlapplicationcontext classpathxmlapplicationcontext =
      //         new classpathxmlapplicationcontext(
      //                 "com/hzw/learn/springboot/springbase/springbean/hello/hellobean.xml");

        BeanFactory beanFactory =
                new ClassPathXmlApplicationContext(
                        "hellobean.xml");

//        ClassPathXmlApplicationContext classPathXmlApplicationContext =
//                new ClassPathXmlApplicationContext(
//                        "springbean.xml");
        // xml 定义bean
        HelloApi hello1 = beanFactory.getBean("hello1", HelloApi.class);
        hello1.sayHello();

        // xml 定义别名
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
    }

}
