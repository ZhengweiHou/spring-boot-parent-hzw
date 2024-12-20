package com.hzw.learn.springboot.springbase.ApplicationEventTest.builtInEvents;

import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

/**
 * @ClassName HzwAllEventListener
 * @Description TODO
 * @Author houzw
 * @Date 2024/6/4
 **/
@Component
public class HzwAllEventListener implements ApplicationListener<ApplicationEvent> {
    @Override
    public void onApplicationEvent(ApplicationEvent event) {
        System.out.println("allEventListener received: " + event.getClass().getSimpleName());
    }
}
