package com.hzw.learn.sofa_base.client;

import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;


@Component
public class EventListener implements ApplicationListener {

    static int times=0;

    @Override
    public void onApplicationEvent(ApplicationEvent applicationEvent) {

        times++;
        if(times % 10 == 0){
            System.out.println(
                    "--times:" + times + " " +
                    Thread.currentThread().getName()+ ":" + applicationEvent.getClass().getSimpleName()
            );
        }

    }

}

