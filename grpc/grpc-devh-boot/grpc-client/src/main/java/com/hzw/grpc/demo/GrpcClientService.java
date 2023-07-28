package com.hzw.grpc.demo;

import com.google.common.util.concurrent.ListenableFuture;
import com.google.protobuf.Any;
import com.google.protobuf.InvalidProtocolBufferException;
import com.google.protobuf.StringValue;
import com.hzw.grpc.*;
import net.devh.boot.grpc.client.inject.GrpcClient;
import org.springframework.stereotype.Service;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

@Service
public class GrpcClientService{
    @GrpcClient("hzwServer")
    private SimpleGrpc.SimpleBlockingStub simpleBlockingStub;

    @GrpcClient("hzwServer")
    private SimpleGrpc.SimpleFutureStub   simpleFutureStub;

    @GrpcClient("hzwServer")
    private Simple2Grpc.Simple2BlockingStub simple2BlockingStub;

    public String sendMessage(String name) {
        HelloRequest request = HelloRequest.newBuilder()
                .setName(name)
                .build();
        return simpleBlockingStub.sayHello(request).getMessage();
    }

    public String sendMessageFuture(String name) {
        HelloRequest request = HelloRequest.newBuilder()
                .setName(name)
                .build();

        ListenableFuture<HelloReply> future = simpleFutureStub.sayHello(request);
        HelloReply rep = null;
        try {
            Thread.sleep(5*1000);
            rep = future.get(60, TimeUnit.SECONDS);
//            rep = future.get();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        } catch (ExecutionException e) {
            throw new RuntimeException(e);
        } catch (TimeoutException e) {
            throw new RuntimeException(e);
        }
        return rep.getMessage();
    }


    public String s2_sendMessage(String name) throws InvalidProtocolBufferException {



        HelloRequest2.Builder rb = HelloRequest2.newBuilder();
        Any anyName = Any.pack(StringValue.newBuilder().setValue(name).build()); // 将一个字符串打包成Any类型
        HelloRequest2 request = rb.putAttributr("name", anyName).build();

        HelloReply2 rep = simple2BlockingStub.sayHello(request);
        String message = rep.getAttributrMap().get("message").unpack(StringValue.class).getValue();
        return message;
    }
}


