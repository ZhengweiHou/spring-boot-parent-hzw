package com.hzw.grpc.demo.server;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.ComponentScan;

/**
 * @ClassName GrpcServerApplication
 * @Description TODO
 * @Author houzw
 * @Date 2023/7/12
 **/
@EnableDiscoveryClient
@SpringBootApplication
@ComponentScan(basePackages = "com.hzw.grpc.demo.server")
public class GrpcServerApplication {

    public static void main(String[] args) {
        SpringApplication.run(GrpcServerApplication.class, args);
    }

}