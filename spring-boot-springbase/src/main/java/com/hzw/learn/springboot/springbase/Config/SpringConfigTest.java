package com.hzw.learn.springboot.springbase.Config;

import com.hzw.learn.springboot.springbase.Transaction.isolation.IsolationSimpleTestService;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @ClassName SpringTxTest
 * @Description TODO
 * @Author houzw
 * @Date 2022/4/25
 **/
@FixMethodOrder(MethodSorters.JVM)
public class SpringConfigTest {
    HzwConfig hzwConfig;

    @Before
    public void init() throws InterruptedException {
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("Config/Config.xml");
        this.hzwConfig = (HzwConfig) applicationContext.getBean("hzwConfig");
        Thread.sleep(100);
    }


    @Test
    public void test1() throws Exception {
        System.out.println(
                hzwConfig.getProperty("HOME")
        );

        String[] actProf = hzwConfig.environment.getActiveProfiles();
        System.out.println("===" + actProf.length);
        for (String s : actProf) {
            System.out.println(s);
        }

        actProf = hzwConfig.environment.getDefaultProfiles();
        System.out.println("===" + actProf.length);
        for (String s : actProf) {
            System.out.println(s);
        }

    }















}
