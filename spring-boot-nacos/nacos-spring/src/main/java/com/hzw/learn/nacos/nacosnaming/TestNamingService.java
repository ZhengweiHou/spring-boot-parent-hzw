package com.hzw.learn.nacos.nacosnaming;

import com.alibaba.nacos.api.annotation.NacosInjected;
import com.alibaba.nacos.api.config.annotation.NacosValue;
import com.alibaba.nacos.api.exception.NacosException;
import com.alibaba.nacos.api.naming.NamingService;
import com.google.gson.Gson;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;


@Service
public class TestNamingService implements ApplicationListener {

    @NacosInjected
    private NamingService namingService;

    @Override
    public void onApplicationEvent(ApplicationEvent applicationEvent) {
        System.out.println(Thread.currentThread().getId() + ":" + applicationEvent.getClass().getSimpleName());
//        try {
//            System.out.println(Thread.currentThread().getId() + ":" + applicationEvent.getClass().getSimpleName() +":" +
//                    new Gson().toJson(namingService.getAllInstances("houzw")));
//        } catch (NacosException e) {
//            e.printStackTrace();
//        }

    }

    @PostConstruct
    public void postConstructMeth() throws InterruptedException {
        new Thread(() ->{
            while (true)
                try {
                    hello(1234);
                    Thread.sleep(5*1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

        }).start();
    }


    private static int port=8000;
    private void hello(int a){
        try {
            // 注册服务
            namingService.registerInstance("houzw","127.0.0.1",port++);
//            namingService.registerInstance("com.hzw.learn.ext.HelloService:DEFAULT","127.0.0.1",port++);

            System.out.println(
                new Gson().toJson(
                    namingService.getAllInstances("houzw")
                )
            );
        } catch (NacosException e) {
            e.printStackTrace();
        }

    }




}

