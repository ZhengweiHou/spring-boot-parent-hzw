package com.hzw.grpc.demo;

import io.grpc.Metadata;
import io.grpc.ServerCall;
import io.grpc.ServerCallHandler;
import io.grpc.ServerInterceptor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class GrpcServerMetaInterceptor implements ServerInterceptor {

    private static final Logger log = LoggerFactory.getLogger(GrpcServerMetaInterceptor.class);

    public <ReqT, RespT> ServerCall.Listener<ReqT> interceptCall(
            ServerCall<ReqT, RespT> serverCall,
            Metadata metadata,
            ServerCallHandler<ReqT, RespT> serverCallHandler) {

        log.info(serverCall.getMethodDescriptor().getFullMethodName());
        String username = metadata.get(Metadata.Key.of("username", Metadata.ASCII_STRING_MARSHALLER));

        String nullValue = metadata.get(Metadata.Key.of("nullkey", Metadata.ASCII_STRING_MARSHALLER));
        System.out.println("====" + nullValue);
        System.out.println(nullValue == null);
        ContextHolder.setUsername(username);

//        ForwardingServerCall.SimpleForwardingServerCall
        return serverCallHandler.startCall(serverCall, metadata);
    }

}
