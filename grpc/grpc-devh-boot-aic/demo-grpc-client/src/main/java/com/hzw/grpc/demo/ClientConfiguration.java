package com.hzw.grpc.demo;

import com.hzw.grpc.demo.api.HzwApi;
import com.hzw.grpc.fram.client.AicGrpcClientFactoryBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @ClassName ClientConfiguration
 * @Description TODO
 * @Author houzw
 * @Date 2023/7/28
 **/
@Configuration(proxyBeanMethods = false)
public class ClientConfiguration {
    @Bean
    public AicGrpcClientFactoryBean hzwApi(){
        return new AicGrpcClientFactoryBean("cps",HzwApi.class);
    }

    @Bean
    public AicGrpcClientFactoryBean hzwApiJdk(){
        AicGrpcClientFactoryBean fb = new AicGrpcClientFactoryBean(HzwApi.class);
        fb.setSerializerType("jdk");
        return fb;
    }
}
