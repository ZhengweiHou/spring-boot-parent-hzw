package com.hzw.grpc;

import net.devh.boot.grpc.server.interceptor.GrpcGlobalServerInterceptor;
import org.springframework.context.annotation.Configuration;

@Configuration(proxyBeanMethods = false)
public class GrpcServerConfiguration {

    @GrpcGlobalServerInterceptor
    GrpcServerInterceptor logServerInterceptor() {
        return new GrpcServerInterceptor();
    }

}
