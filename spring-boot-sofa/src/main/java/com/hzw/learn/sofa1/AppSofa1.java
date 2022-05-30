package com.hzw.learn.sofa1;

import com.alipay.lookout.api.Registry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.PropertySource;

@SpringBootApplication
@PropertySource("classpath:appSofa1.properties")
public class  AppSofa1 {
    @Autowired
    private Registry registry;

//    @PostConstruct
//    public void init() {
//        Counter counter = registry.counter(registry.createId("http_requests_total").withTag("instant", NetworkUtil.getLocalAddress().getHostName()));
//        counter.inc();
//    }

    public static void main(String[] args) {
        SpringApplication.run(AppSofa1.class,args);

    }

    // java -Dserver.port=8003 -Dserver.port.start=12201 -Dvport=8003 -jar sofa1.jar
}
