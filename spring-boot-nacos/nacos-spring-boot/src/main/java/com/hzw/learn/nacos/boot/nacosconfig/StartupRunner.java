package com.hzw.learn.nacos.boot.nacosconfig;

import com.alibaba.nacos.spring.context.event.config.NacosConfigReceivedEvent;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.context.EnvironmentAware;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

/**
 * @ClassName StartupRunner
 * @Description TODO
 * @Author houzw
 * @Date 2024/9/10
 **/
@Component
public class StartupRunner implements ApplicationRunner, EnvironmentAware, ApplicationListener<ApplicationEvent> {
    @Autowired
    HzwProperties hzwProperties;

    @Autowired
    Environment environment;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        System.out.println("===" + new Gson().toJson(hzwProperties));
    }


    @Override
    public void setEnvironment(Environment environment) {
        environment = environment;
    }


    @Override
    public void onApplicationEvent(ApplicationEvent event) {
        System.out.println("============event = " + event.getClass().getName() + "  ===" + new Gson().toJson(hzwProperties));
    }
}
