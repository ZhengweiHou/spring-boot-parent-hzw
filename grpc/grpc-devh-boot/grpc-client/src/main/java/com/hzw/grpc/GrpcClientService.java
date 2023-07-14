package com.hzw.grpc;

import net.devh.boot.grpc.client.inject.GrpcClient;
import org.springframework.stereotype.Service;

@Service
public class GrpcClientService{
    @GrpcClient("hzwService")
    private SimpleGrpc.SimpleBlockingStub hzwServiceStub;

    public String sendMessage(String name) {
        HelloRequest request = HelloRequest.newBuilder()
                .setName(name)
                .build();
        return hzwServiceStub.sayHello(request).getMessage();
    }
}


