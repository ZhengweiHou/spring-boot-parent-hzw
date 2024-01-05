package com.hzw.grpc.demo;

import com.hzw.grpc.demo.xxx.XxxBean;
import com.hzw.grpc.demo.xxx.XxxBeanDependencyBean;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
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
        ConfigurableApplicationContext cont = SpringApplication.run(GrpcClientApplication.class, args);
        XxxBean xxxBean = (XxxBean) cont.getBean("xxxFB");
        System.out.println(xxxBean.xxxBeanDependencyBean.name);
    }
}