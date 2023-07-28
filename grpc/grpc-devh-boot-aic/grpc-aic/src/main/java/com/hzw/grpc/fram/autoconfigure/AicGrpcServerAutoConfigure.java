package com.hzw.grpc.fram.autoconfigure;

import com.hzw.grpc.fram.server.*;
import net.devh.boot.grpc.server.autoconfigure.GrpcServerAutoConfiguration;
import net.devh.boot.grpc.server.service.GrpcServiceDefinition;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @ClassName AicGrpcServerAutoConfigure
 * @Description TODO
 * @Author houzw
 * @Date 2023/7/24
 **/
@Configuration(proxyBeanMethods = false)
@AutoConfigureAfter(GrpcServerAutoConfiguration.class)
public class AicGrpcServerAutoConfigure {

    @ConditionalOnMissingBean
    @Bean
    public AicGrpcServiceDiscoverer defaultAicGrpcServiceDiscoverer() {
        return new AicGrpcServiceAnnotationDiscoverer();
    }

    @ConditionalOnMissingBean
    @Bean
    public AicGrpcServiceFactory aicGrpcServiceFactory(
            final AicGrpcServiceDiscoverer aicGrpcServiceDiscoverer) {
        // Aic grpc 服务工厂初始化
        final AicGrpcServiceFactory factory = new AicGrpcServiceFactory();
        for (final AicGrpcServiceDefinition service : aicGrpcServiceDiscoverer.findAicGrpcServiceDefinitions()) {
            factory.addServiceDefinition(service.getBeanClazz(),service);
        }
        return factory;
    }

    @Bean
    public AicGrpcHandler aicGrpcHandler(AicGrpcServiceFactory aicGrpcServiceFactory){
        return new AicGrpcHandler(aicGrpcServiceFactory);
    }

}
