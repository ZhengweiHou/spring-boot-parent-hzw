package com.hzw.learn.nacos.nacosconfig;

import com.alibaba.nacos.api.config.annotation.NacosValue;
import com.alibaba.nacos.spring.context.event.config.NacosConfigReceivedEvent;
import com.google.gson.Gson;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.Date;
import java.util.List;


@Service
public class ConfigService implements ApplicationListener<NacosConfigReceivedEvent> {


    @NacosValue(value = "${a:xx}", autoRefreshed = true)
    private String a;
    @NacosValue(value = "${b:xx}", autoRefreshed = true)
    private String b;

    @NacosValue(value = "${c:xx}", autoRefreshed = true)
    private List<String> c;

//    @Override
//    public void onApplicationEvent(ApplicationEvent applicationEvent) {
//        if(applicationEvent instanceof NacosConfigReceivedEvent){
//            NacosConfigReceivedEvent even = (NacosConfigReceivedEvent) applicationEvent;
//            System.out.println(
//                    Thread.currentThread().getName()+ ":"
//                            + even.getClass().getSimpleName()
//                            + " dataid:" + even.getDataId()
//                            + " a=" + a + " b=" + b);
//        }
//    }

    @Override
    public void onApplicationEvent(NacosConfigReceivedEvent event) {
        System.out.println(
                Thread.currentThread().getName()+ ":"
                        + event.getClass().getSimpleName()
                        + " dataid:" + event.getDataId()
                        + " a=" + a + " b=" + b + " c=" + new Gson().toJson(c));
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

