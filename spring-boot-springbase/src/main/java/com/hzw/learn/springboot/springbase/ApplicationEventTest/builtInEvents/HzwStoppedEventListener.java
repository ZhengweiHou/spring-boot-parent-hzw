package com.hzw.learn.springboot.springbase.ApplicationEventTest.builtInEvents;

import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextStoppedEvent;
import org.springframework.stereotype.Component;

/**
 * @ClassName HzwContextStoppedEvent
 * @Description TODO
 * @Author houzw
 * @Date 2024/6/4
 **/
@Component
public class HzwStoppedEventListener implements ApplicationListener<ContextStoppedEvent> {
    @Override
    public void onApplicationEvent(ContextStoppedEvent event) {
        System.out.println(getClass().getSimpleName());
    }
}
