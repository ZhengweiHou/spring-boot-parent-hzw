package com.hzw.grpc.demo;
import com.hzw.grpc.ExampleProto;
import com.hzw.grpc.ExampleServiceGrpc;
import io.grpc.Server;
import io.grpc.ServerBuilder;
import io.grpc.stub.StreamObserver;

import java.io.IOException;

public class ExampleServer {
    private Server server;

    public static void main(String[] args) throws IOException, InterruptedException {
        ExampleServer exampleServer = new ExampleServer();
        exampleServer.start();
        exampleServer.blockUntilShutdown();
    }

    private void start() throws IOException {
        int port = 8082;
        server = ServerBuilder.forPort(port)
                .addService(new ExampleServiceImplementation())
                .build()
                .start();
        System.out.println("Server started, listening on " + port);
    }

    private void blockUntilShutdown() throws InterruptedException {
        if (server != null) {
            server.awaitTermination();
        }
    }

    private static class ExampleServiceImplementation extends ExampleServiceGrpc.ExampleServiceImplBase {
        @Override
        public void sayHello(ExampleProto.HelloRequest request, StreamObserver<ExampleProto.HelloResponse> responseObserver) {
            String name = request.getName();
            System.out.println("get msg name:" + name);
            String message = "Hello, " + name + "!";
            ExampleProto.HelloResponse response = ExampleProto.HelloResponse.newBuilder().setMessage(message).build();
            responseObserver.onNext(response);
            responseObserver.onCompleted();
        }
    }
}
