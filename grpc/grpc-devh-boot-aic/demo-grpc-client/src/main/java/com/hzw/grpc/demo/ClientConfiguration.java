package com.hzw.grpc.demo;

import com.hzw.grpc.demo.api.HzwApi;
import com.hzw.grpc.fram.client.AicGrpcClientFactoryBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;

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
//        return new AicGrpcClientFactoryBean(HzwApi.class);
    }

    @Bean
    public AicGrpcClientFactoryBean hzwApiJdk(){
        AicGrpcClientFactoryBean fb = new AicGrpcClientFactoryBean();
        fb.setInterfaceClass(HzwApi.class);
        fb.setSerializerType("jdk");
        return fb;
    }
}
