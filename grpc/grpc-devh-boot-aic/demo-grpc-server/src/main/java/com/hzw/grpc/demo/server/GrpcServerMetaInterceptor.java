package com.hzw.grpc.demo.server;

import io.grpc.*;
import net.devh.boot.grpc.server.interceptor.GrpcGlobalServerInterceptor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@GrpcGlobalServerInterceptor
public class GrpcServerMetaInterceptor implements ServerInterceptor {


    Logger log = LoggerFactory.getLogger(getClass());

    public <ReqT, RespT> ServerCall.Listener<ReqT> interceptCall(
            ServerCall<ReqT, RespT> serverCall,
            Metadata metadata,
            ServerCallHandler<ReqT, RespT> serverCallHandler) {
        log.info("GrpcServerMetaInterceptor");
        String username = metadata.get(Metadata.Key.of("username", Metadata.ASCII_STRING_MARSHALLER));
        Context ctx = Context.current().withValue(ContextHolder.METADATA_CONTEXT_KEY,metadata);

        ServerCall.Listener<ReqT> listenerWithContext = Contexts
                .interceptCall(ctx, serverCall, metadata, serverCallHandler);

        // 创建自定义的 Listener，将 context 中的元数据传递给业务代码
        ServerCall.Listener<ReqT> xxListenerWithContext =
                new ForwardingServerCallListener.SimpleForwardingServerCallListener<ReqT>(
//                        listenerWithContext
                        serverCallHandler.startCall(serverCall,metadata)
                ) {
                    @Override
                    public void onMessage(ReqT message) {
                        log.info("GrpcServerMetaInterceptor->ServerCallListener.onMessage");
                        super.onMessage(message);
                    }

                    @Override
                    public void onHalfClose() {
                        log.info("GrpcServerMetaInterceptor->ServerCallListener.onHalfClose");
                        Context ctx2 = null;
                        try {
                            ctx2 = ctx.attach();
                            Metadata metadata = ContextHolder.METADATA_CONTEXT_KEY.get();
                            if (metadata != null) {
                                String username = metadata.get(ContextHolder.username_meta_key);
                                ContextHolder.setUsername(username);
                            }
                            super.onHalfClose();
                        } finally {
                            if (ctx != null) {
                                ctx.detach(ctx2);
                            }
                            ContextHolder.username.remove();
                        }
                        log.info("GrpcServerMetaInterceptor->ServerCallListener.onHalfClose--2");
                    }
                };
        return xxListenerWithContext;
    }


//    public <ReqT, RespT> ServerCall.Listener<ReqT> interceptCall(
//            ServerCall<ReqT, RespT> serverCall,
//            Metadata metadata,
//            ServerCallHandler<ReqT, RespT> serverCallHandler) {
//
//        Context.current().withValue()
//
//        log.info(serverCall.getMethodDescriptor().getFullMethodName());
//        String username = metadata.get(Metadata.Key.of("username", Metadata.ASCII_STRING_MARSHALLER));
//
//        String nullValue = metadata.get(Metadata.Key.of("nullkey", Metadata.ASCII_STRING_MARSHALLER));
//        System.out.println("====" + nullValue);
//        System.out.println(nullValue == null);
//        ContextHolder.setUsername(username);
//
////        ForwardingServerCall.SimpleForwardingServerCall
//        return serverCallHandler.startCall(serverCall, metadata);
//    }

}
