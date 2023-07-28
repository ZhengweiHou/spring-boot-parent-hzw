package com.hzw.grpc.demo;

import com.hzw.grpc.ExampleProto;
import com.hzw.grpc.ExampleServiceGrpc;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;

import java.util.concurrent.TimeUnit;

/**
 * @ClassName ExampleClient
 * @Description TODO
 * @Author houzw
 * @Date 2023/7/11
 **/

public class ExampleClient {
    private final ManagedChannel channel;
    private final ExampleServiceGrpc.ExampleServiceBlockingStub blockingStub;

    public ExampleClient(String host, int port) {
        channel = ManagedChannelBuilder.forAddress(host, port)
                .usePlaintext()
                .build();
        blockingStub = ExampleServiceGrpc.newBlockingStub(channel);
    }

    public void shutdown() throws InterruptedException {
        channel.shutdown().awaitTermination(5, TimeUnit.SECONDS);
    }

    public void sayHello(String name) {
        ExampleProto.HelloRequest request = ExampleProto.HelloRequest.newBuilder().setName(name).build();
        ExampleProto.HelloResponse response = blockingStub.sayHello(request);
        System.out.println("Server response: " + response.getMessage());
    }

    public static void main(String[] args) throws InterruptedException {
        ExampleClient exampleClient = new ExampleClient("localhost", 8082);
        exampleClient.sayHello("hzw");
        exampleClient.shutdown();
    }
}
