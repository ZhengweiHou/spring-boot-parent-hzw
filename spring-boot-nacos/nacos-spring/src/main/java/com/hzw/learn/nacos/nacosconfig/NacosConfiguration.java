package com.hzw.learn.nacos.nacosconfig;

import com.alibaba.nacos.api.annotation.NacosProperties;
import com.alibaba.nacos.spring.context.annotation.config.EnableNacosConfig;
import com.alibaba.nacos.spring.context.annotation.config.NacosPropertySource;
import com.alibaba.nacos.spring.context.annotation.config.NacosValueAnnotationBeanPostProcessor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
//@EnableNacosConfig(globalProperties = @NacosProperties(serverAddr = "127.0.0.1:8848",namespace = "public"))
@EnableNacosConfig(globalProperties = @NacosProperties(serverAddr = "127.0.0.1:40905",namespace = "test"))
//@EnableNacosConfig(globalProperties = @NacosProperties(serverAddr = "192.168.104.139:8848",namespace = "test"))
//@EnableNacosConfig(globalProperties = @NacosProperties(serverAddr = "10.252.22.31:8848",namespace = "test"))
@NacosPropertySource(dataId = "test",groupId = "test",autoRefreshed = true)
public class NacosConfiguration {
//    @Bean
//    public NacosValueAnnotationBeanPostProcessor nbabpp(){
//        System.out.println("xxxxxxxxxxxx");
//        return new NacosValueAnnotationBeanPostProcessor();
//    }

}
