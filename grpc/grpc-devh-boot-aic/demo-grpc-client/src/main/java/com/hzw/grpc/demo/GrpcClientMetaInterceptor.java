package com.hzw.grpc.demo;

import io.grpc.*;
import net.devh.boot.grpc.client.interceptor.GrpcGlobalClientInterceptor;

import java.security.ProtectionDomain;

@GrpcGlobalClientInterceptor
public class GrpcClientMetaInterceptor implements ClientInterceptor {

    @Override
    public <ReqT, RespT> ClientCall<ReqT, RespT> interceptCall(
            MethodDescriptor<ReqT, RespT> method,
            CallOptions callOptions,
            Channel next) {

        return new ForwardingClientCall.SimpleForwardingClientCall<ReqT, RespT>(next.newCall(method, callOptions)) {
            @Override
            public void start(Listener<RespT> responseListener, Metadata headers) {
                // 在这里添加额外的元数据信息
//                headers.put(Metadata.Key.of("username", Metadata.ASCII_STRING_MARSHALLER), "houzw-interceptro");
                headers.put(Metadata.Key.of("username", Metadata.ASCII_STRING_MARSHALLER), "houzw-zzzzzzzzzzzzzzzzzzzzzz");
                super.start(responseListener, headers);
            }
        };

    }

}
