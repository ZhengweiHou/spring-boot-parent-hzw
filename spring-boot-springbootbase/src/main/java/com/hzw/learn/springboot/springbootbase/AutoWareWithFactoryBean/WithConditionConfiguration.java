package com.hzw.learn.springboot.springbootbase.AutoWareWithFactoryBean;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Conditional;
import org.springframework.context.annotation.Configuration;

/**
 * @ClassName WithConditionConfiguration
 * @Description TODO
 * @Author houzw
 * @Date 2023/8/2
 **/
@Configuration
@Conditional(TestCondition.class)
public class WithConditionConfiguration {
    @Bean
    public String valueCondition1(){
        return "hello1";
    }
}
