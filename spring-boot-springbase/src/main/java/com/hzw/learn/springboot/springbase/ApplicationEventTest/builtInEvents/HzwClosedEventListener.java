package com.hzw.learn.springboot.springbase.ApplicationEventTest.builtInEvents;

import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextClosedEvent;
import org.springframework.stereotype.Component;

/**
 * @ClassName HzwContextClosedEvent
 * @Description TODO
 * @Author houzw
 * @Date 2024/6/4
 **/
@Component
public class HzwClosedEventListener implements ApplicationListener<ContextClosedEvent> {
    @Override
    public void onApplicationEvent(ContextClosedEvent event) {
        System.out.println(getClass().getSimpleName());

//        StackTraceElement[] stackTrace = Thread.currentThread().getStackTrace();
//        for (StackTraceElement element : stackTrace) {
//            System.out.println(element.toString());
//        }
    }
}
