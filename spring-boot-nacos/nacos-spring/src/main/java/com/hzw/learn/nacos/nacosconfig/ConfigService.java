package com.hzw.learn.nacos.nacosconfig;

import com.alibaba.nacos.api.config.annotation.NacosValue;
import com.alibaba.nacos.spring.context.event.config.NacosConfigReceivedEvent;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.Date;


@Service
public class ConfigService implements ApplicationListener {


    @NacosValue(value = "${a:xx}", autoRefreshed = true)
    private String a;
    @NacosValue(value = "${b:xx}", autoRefreshed = true)
    private String b;

    @Override
    public void onApplicationEvent(ApplicationEvent applicationEvent) {
        if(applicationEvent instanceof NacosConfigReceivedEvent){
            NacosConfigReceivedEvent even = (NacosConfigReceivedEvent) applicationEvent;
            System.out.println(
                    Thread.currentThread().getName()+ ":"
                            + even.getClass().getSimpleName()
                            + " dataid:" + even.getDataId()
                            + " a=" + a + " b=" + b);

        }

    }

    @PostConstruct
    public void postConstructMeth() throws InterruptedException {
        showab();
//        while (true){ // 不可以在PostConstruct处阻塞，会导致IOC容器无法完成初始化
//            Thread.sleep(3000);
//            showab();
//        }
    }

    private void showab(){
        System.out.println("PostConstruct:" + this.a + ":" + this.b);
    }




}

