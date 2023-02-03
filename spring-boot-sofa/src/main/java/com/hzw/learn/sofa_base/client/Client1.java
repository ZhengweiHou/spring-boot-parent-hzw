package com.hzw.learn.sofa_base.client;

import com.alipay.lookout.api.Registry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.PropertySource;

@SpringBootApplication
@PropertySource("classpath:sofa_base/client.properties")
public class Client1 {
    @Autowired
    private Registry registry;

//    @PostConstruct
//    public void init() {
//        Counter counter = registry.counter(registry.createId("http_requests_total").withTag("instant", NetworkUtil.getLocalAddress().getHostName()));
//        counter.inc();
//    }

    public static void main(String[] args) {

        SpringApplication.run(Client1.class,args);

    }
}
