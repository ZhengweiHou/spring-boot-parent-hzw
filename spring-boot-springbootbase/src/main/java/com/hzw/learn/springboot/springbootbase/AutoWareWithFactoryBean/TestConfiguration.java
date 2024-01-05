package com.hzw.learn.springboot.springbootbase.AutoWareWithFactoryBean;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @ClassName TestConfiguration
 * @Description TODO
 * @Author houzw
 * @Date 2023/8/2
 **/
//@Configuration
public class TestConfiguration {
    @Bean
    public TestFactoryBean valueFactoryBean1(){
        return new TestFactoryBean();
    }

    @Bean
    public HzwBean hzwBean(){
        return new HzwBean("sirius");
    }
}
