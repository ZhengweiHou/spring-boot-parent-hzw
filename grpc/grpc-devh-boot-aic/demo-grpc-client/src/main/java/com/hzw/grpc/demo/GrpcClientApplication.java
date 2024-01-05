package com.hzw.grpc.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.ApplicationPidFileWriter;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.ImportResource;

/**
 * @ClassName GrpcClientApplication
 * @Description TODO
 * @Author houzw
 * @Date 2023/7/12
 **/
@EnableDiscoveryClient
@SpringBootApplication
@ImportResource(locations = "classpath:client.xml")
public class GrpcClientApplication {

    public static void main(String[] args) {
        ConfigurableApplicationContext app = SpringApplication.run(GrpcClientApplication.class, args);
//        app.addApplicationListener(new ApplicationPidFileWriter());
    }
}