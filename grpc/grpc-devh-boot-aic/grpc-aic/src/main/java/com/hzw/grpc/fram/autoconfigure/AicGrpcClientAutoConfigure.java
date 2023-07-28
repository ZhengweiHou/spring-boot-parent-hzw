package com.hzw.grpc.fram.autoconfigure;

import com.hzw.grpc.fram.client.AicGrpcClientBeanPostProcessor;
import com.hzw.grpc.fram.client.AicGrpcClientFactoryBean;
import com.hzw.grpc.fram.server.AicGrpcServiceDiscoverer;
import net.devh.boot.grpc.client.autoconfigure.GrpcClientAutoConfiguration;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @ClassName AicGrpcClientAutoConfigure
 * @Description TODO
 * @Author houzw
 * @Date 2023/7/24
 **/
@Configuration(proxyBeanMethods = false)
@AutoConfigureAfter(GrpcClientAutoConfiguration.class)
public class AicGrpcClientAutoConfigure {

    @Bean
    public AicGrpcClientBeanPostProcessor aicClientBeanPostProcessor(final ApplicationContext applicationContext){
        return new AicGrpcClientBeanPostProcessor(applicationContext);
    }

}
