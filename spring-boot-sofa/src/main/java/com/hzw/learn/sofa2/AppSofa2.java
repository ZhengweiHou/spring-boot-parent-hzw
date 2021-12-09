package com.hzw.learn.sofa2;

import com.alipay.lookout.api.Counter;
import com.alipay.lookout.api.Registry;
import com.alipay.lookout.common.utils.NetworkUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.PropertySource;

import javax.annotation.PostConstruct;

@SpringBootApplication
@PropertySource("classpath:appSofa2.properties")
public class AppSofa2 {
    @Autowired
    private Registry registry;

    @PostConstruct
    public void init() {
        Counter counter = registry.counter(registry.createId("http_requests_total").withTag("instant", NetworkUtil.getLocalAddress().getHostName()));
        counter.inc();
    }

    public static void main(String[] args) {
        SpringApplication.run(AppSofa2.class,args);
    }
}
