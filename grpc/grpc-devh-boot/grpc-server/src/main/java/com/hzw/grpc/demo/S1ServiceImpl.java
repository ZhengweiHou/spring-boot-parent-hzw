package com.hzw.grpc.demo;

import com.hzw.grpc.HelloReply;
import com.hzw.grpc.HelloRequest;
import com.hzw.grpc.SimpleGrpc;
import io.grpc.stub.StreamObserver;
import net.devh.boot.grpc.server.service.GrpcService;

@GrpcService
public class S1ServiceImpl extends SimpleGrpc.SimpleImplBase {
        @Override
        public void sayHello(HelloRequest request, StreamObserver<HelloReply> responseObserver) {
            String name = request.getName();
            System.out.println("get msg name:" + name);
            String message = "Hello, " + name + "!  username:" + ContextHolder.getUsername();


            HelloReply response = HelloReply.newBuilder().setMessage(message).build();
            int a = 1;
            int b = 0;
//            System.out.println(a/b);
            responseObserver.onNext(response);
            responseObserver.onCompleted();
    }
}
