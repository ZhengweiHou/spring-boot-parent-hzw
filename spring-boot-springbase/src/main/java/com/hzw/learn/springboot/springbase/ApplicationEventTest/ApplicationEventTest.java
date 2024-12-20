package com.hzw.learn.springboot.springbase.ApplicationEventTest;

import com.hzw.learn.springboot.springbase.ApplicationEventTest.customEvents.HzwEvent;
import com.hzw.learn.springboot.springbase.DIAndIOC.DIAutowire.ConstructorAutowiredTestBean;
import com.hzw.learn.springboot.springbase.DIAndIOC.DIAutowire.SonTestBean;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.Map;

/**
 * @ClassName ApplicationEventTest
 * @Description TODO
 * @Author houzw
 * @Date 2024/6/4
 **/

@FixMethodOrder(MethodSorters.JVM)
public class ApplicationEventTest {
    ApplicationContext applicationContext = null;
    @Before
    public void init(){
        System.out.println("===init start===");
        applicationContext = new ClassPathXmlApplicationContext("ApplicationEventTest/applicationEventTest.xml");
        System.out.println("===init end===");
    }

    @Test
    public void applicationEventTest() throws InterruptedException {
        Map<String,Object> beansMap =  applicationContext.getBeansOfType(Object.class);
//        beansMap.forEach((k,v) -> {
//            System.out.println("k:" + k + " v:" + v.getClass().getSimpleName());
//        });

        // 发布自定义事件
        applicationContext.publishEvent(new HzwEvent("hzw","hzwmsg"));

//        ((ClassPathXmlApplicationContext) applicationContext).registerShutdownHook();
        ((ClassPathXmlApplicationContext) applicationContext).refresh();
        ((ClassPathXmlApplicationContext) applicationContext).start();
        ((ClassPathXmlApplicationContext) applicationContext).stop();
        ((ClassPathXmlApplicationContext) applicationContext).close();
    }
}
