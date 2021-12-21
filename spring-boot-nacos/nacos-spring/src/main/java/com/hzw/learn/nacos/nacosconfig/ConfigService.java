package com.hzw.learn.nacos.nacosconfig;

import com.alibaba.nacos.api.config.annotation.NacosValue;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.Date;


@Service
public class ConfigService implements ApplicationListener {


    @NacosValue(value = "${aa:xx}", autoRefreshed = true)
    private String aa;
    @NacosValue(value = "${bb:xx}", autoRefreshed = true)
    private String bb;

    @Override
    public void onApplicationEvent(ApplicationEvent applicationEvent) {

        System.out.println(Thread.currentThread().getId() + ":" + applicationEvent.getClass().getSimpleName() + ":" + aa + ":" + bb);

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
        System.out.println("PostConstruct:" + this.aa + ":" + this.bb);
    }




}

