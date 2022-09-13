package com.hzw.learn.sofa_consumer2multiprovider.client;

import com.alipay.sofa.rpc.config.RegistryConfig;
import com.alipay.sofa.rpc.invoke.Invoker;
import com.hzw.learn.ext.HelloService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.concurrent.ConcurrentHashMap;

@RestController
public class Client2Controller {

    Logger logger = LoggerFactory.getLogger(Client2Controller.class);

    static int times=0;
    static ConcurrentHashMap<String,Invoker> invokers = new ConcurrentHashMap<>();


    @Autowired
    RegistryConfig registryConfig;


    @Autowired
    HelloService helloService;

    @RequestMapping("/hello")
    public String hello(){
        return "hello";
    }

    @RequestMapping("/test")
    @ResponseBody
    public String test(@RequestParam String i){
        String rsp = helloService.hello(i);
        return rsp;
    }


}
