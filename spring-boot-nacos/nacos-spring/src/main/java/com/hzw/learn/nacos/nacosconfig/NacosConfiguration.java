package com.hzw.learn.nacos.nacosconfig;

import com.alibaba.nacos.api.annotation.NacosProperties;
import com.alibaba.nacos.api.config.ConfigType;
import com.alibaba.nacos.api.config.annotation.NacosConfigurationProperties;
import com.alibaba.nacos.spring.context.annotation.config.EnableNacosConfig;
import com.alibaba.nacos.spring.context.annotation.config.NacosPropertySource;
import com.alibaba.nacos.spring.context.annotation.config.NacosPropertySources;
import com.alibaba.nacos.spring.context.annotation.config.NacosValueAnnotationBeanPostProcessor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
// nacos地址可以通过-D方式指定，如：-Dnacos.server-addr=127.0.0.1:8148 -Dnacos.namespace=test
//@EnableNacosConfig(globalProperties = @NacosProperties(serverAddr = "127.0.0.1:8848",namespace = "test"))
@EnableNacosConfig(globalProperties = @NacosProperties(serverAddr = "127.0.0.1:8813",namespace = "test"))
@NacosPropertySources(
        {
//                @NacosPropertySource(dataId = "test",groupId = "test",autoRefreshed = true,type = ConfigType.JSON),
                @NacosPropertySource(dataId = "test",groupId = "test",autoRefreshed = true,type = ConfigType.YAML)
        }
)
//@NacosConfigurationProperties(dataId = "test",groupId = "test",autoRefreshed = true,type = ConfigType.JSON)
//@NacosConfigurationProperties(dataId = "test",groupId = "test",autoRefreshed = true,type = ConfigType.YAML)
//@NacosPropertySource(dataId = "test2",groupId = "test",autoRefreshed = true)
public class NacosConfiguration {
//    @Bean
//    public NacosValueAnnotationBeanPostProcessor nbabpp(){
//        System.out.println("xxxxxxxxxxxx");
//        return new NacosValueAnnotationBeanPostProcessor();
//    }

}
