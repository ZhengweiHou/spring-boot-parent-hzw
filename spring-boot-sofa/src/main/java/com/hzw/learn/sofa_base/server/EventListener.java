package com.hzw.learn.sofa_base.server;

import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;


@Component
public class EventListener implements ApplicationListener {

    @Override
    public void onApplicationEvent(ApplicationEvent applicationEvent) {
        System.out.println(
                Thread.currentThread().getName()+ ":" + applicationEvent.getClass().getSimpleName()
        );
    }

}

