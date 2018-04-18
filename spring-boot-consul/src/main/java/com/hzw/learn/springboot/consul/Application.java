package com.hzw.learn.springboot.consul;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@EnableDiscoveryClient
@ComponentScan(basePackages={"com.hzw.learn.springboot"}) 
public class Application {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args); 
	}
}
