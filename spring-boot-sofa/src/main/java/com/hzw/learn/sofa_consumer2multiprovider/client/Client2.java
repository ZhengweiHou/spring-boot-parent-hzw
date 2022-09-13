package com.hzw.learn.sofa_consumer2multiprovider.client;

import com.alipay.lookout.api.Registry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.PropertySource;

@SpringBootApplication
@PropertySource("classpath:sofa_consumer2multiprovider_client.properties")
public class Client2 {
    @Autowired
    private Registry registry;

    public static void main(String[] args) {
        SpringApplication.run(Client2.class,args);
    }
}
