package com.hzw.learn.sofa_consumer2multiprovider.server;

import com.alipay.lookout.api.Registry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.PropertySource;

@SpringBootApplication
@PropertySource("classpath:sofa_consumer2multiprovider_server.properties")
public class Server2 {
    @Autowired
    private Registry registry;


    public static void main(String[] args) {
//        System.getProperties().put("default.uniqueId",args[0]);
//        System.getProperties().put("default.uniqueId","hzw");
        SpringApplication.run(Server2.class,args);
    }

    // java -Dserver.port=8003 -Dserver.port.start=12201 -Dvport=8003 -Duniqueid=1 -jar sofa1.jar
}
