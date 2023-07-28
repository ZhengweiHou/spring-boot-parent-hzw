package com.hzw.grpc.demo;

import com.google.protobuf.Any;
import com.google.protobuf.InvalidProtocolBufferException;
import com.google.protobuf.StringValue;
import com.hzw.grpc.HelloReply2;
import com.hzw.grpc.HelloRequest2;
import com.hzw.grpc.Simple2Grpc;
import io.grpc.stub.StreamObserver;
import net.devh.boot.grpc.server.service.GrpcService;

@GrpcService
public class S2ServiceImpl extends Simple2Grpc.Simple2ImplBase {
    @Override
    public void sayHello(HelloRequest2 request, StreamObserver<HelloReply2> responseObserver) {
        String name = null;
        try {
            name = request.getAttributrMap().get("name").unpack(StringValue.class).getValue();
        } catch (InvalidProtocolBufferException e) {
            throw new RuntimeException(e);
        }
        System.out.println("get msg name:" + name);
        String message = "Hello, " + name + "!  username:" + ContextHolder.getUsername();

        HelloReply2 response = HelloReply2.newBuilder().putAttributr("message",
                Any.pack(StringValue.newBuilder().setValue(message).build())
                ).build();
        responseObserver.onNext(response);
        responseObserver.onCompleted();
    }
}
