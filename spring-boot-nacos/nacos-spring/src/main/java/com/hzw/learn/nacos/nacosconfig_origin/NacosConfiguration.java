package com.hzw.learn.nacos.nacosconfig_origin;

import com.alibaba.nacos.api.annotation.NacosProperties;
import com.alibaba.nacos.spring.context.annotation.config.EnableNacosConfig;
import com.alibaba.nacos.spring.context.annotation.config.NacosPropertySource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class NacosConfiguration {
    @Bean
    public WhiteListHolder whiteListHolder(){
        return new WhiteListHolder();
    }

//    @Bean
//    public NacosValueAnnotationBeanPostProcessor nbabpp(){
//        System.out.println("xxxxxxxxxxxx");
//        return new NacosValueAnnotationBeanPostProcessor();
//    }

}
