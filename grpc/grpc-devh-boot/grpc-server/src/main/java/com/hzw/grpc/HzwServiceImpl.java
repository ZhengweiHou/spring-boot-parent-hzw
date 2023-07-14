package com.hzw.grpc;

import io.grpc.stub.StreamObserver;
import net.devh.boot.grpc.server.service.GrpcService;

@GrpcService
public class HzwServiceImpl extends SimpleGrpc.SimpleImplBase {
        @Override
        public void sayHello(HelloRequest request, StreamObserver<HelloReply> responseObserver) {
            String name = request.getName();
            System.out.println("get msg name:" + name);
            String message = "Hello, " + name + "!";
            HelloReply response = HelloReply.newBuilder().setMessage(message).build();
            responseObserver.onNext(response);
            responseObserver.onCompleted();
    }
}
