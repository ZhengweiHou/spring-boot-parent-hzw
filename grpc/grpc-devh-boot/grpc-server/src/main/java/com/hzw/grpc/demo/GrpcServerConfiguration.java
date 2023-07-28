package com.hzw.grpc.demo;

import net.devh.boot.grpc.server.interceptor.GrpcGlobalServerInterceptor;
import org.springframework.context.annotation.Configuration;

@Configuration(proxyBeanMethods = false)
public class GrpcServerConfiguration {

    @GrpcGlobalServerInterceptor
    GrpcServerInterceptor logServerInterceptor() {
        return new GrpcServerInterceptor();
    }


    @GrpcGlobalServerInterceptor
    GrpcServerMetaInterceptor grpcServerMetaInterceptor() {
        return new GrpcServerMetaInterceptor();
    }

}
