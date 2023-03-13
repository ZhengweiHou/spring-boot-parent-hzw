package com.hzw.learn.springboot.springbase.DIAndIOC.scop;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

import java.util.ArrayList;

/**
 * @ClassName ScopTestConfiguration
 * @Description TODO
 * @Author houzw
 * @Date 2023/2/21
 **/
@Configuration
public class ScopTestConfiguration {

    @Bean("listBeans")
    public ArrayList<Object> listBeans(){
        ArrayList<Object> listBeans = new ArrayList<>();
        listBeans.add(aBean());
        listBeans.add(bBean());
        return listBeans;
    }

    @Bean
    public ABean aBean(){
        return new ABean();
    }

    @Bean
    public BBean bBean(){
        return new BBean();
    }

}
