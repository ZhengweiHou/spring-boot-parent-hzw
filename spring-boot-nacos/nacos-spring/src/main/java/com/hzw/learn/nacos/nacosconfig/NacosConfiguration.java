package com.hzw.learn.nacos.nacosconfig;

import com.alibaba.nacos.api.annotation.NacosProperties;
import com.alibaba.nacos.spring.context.annotation.config.EnableNacosConfig;
import com.alibaba.nacos.spring.context.annotation.config.NacosPropertySource;
import com.alibaba.nacos.spring.context.annotation.config.NacosValueAnnotationBeanPostProcessor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
// nacos地址可以通过-D方式指定，如：-Dnacos.server-addr=127.0.0.1:8148 -Dnacos.namespace=test
@EnableNacosConfig(globalProperties = @NacosProperties(serverAddr = "127.0.0.1:8148",namespace = "test"))
@NacosPropertySource(dataId = "test",groupId = "test",autoRefreshed = true)
public class NacosConfiguration {
//    @Bean
//    public NacosValueAnnotationBeanPostProcessor nbabpp(){
//        System.out.println("xxxxxxxxxxxx");
//        return new NacosValueAnnotationBeanPostProcessor();
//    }

}
