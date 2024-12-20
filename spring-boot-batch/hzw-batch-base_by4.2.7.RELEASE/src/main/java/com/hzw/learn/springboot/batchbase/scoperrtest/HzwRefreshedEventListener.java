package com.hzw.learn.springboot.batchbase.scoperrtest;

import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * @ClassName HzwContextRefreshedEvent
 * @Description TODO
 * @Author houzw
 * @Date 2024/6/4
 **/
@Component
public class HzwRefreshedEventListener implements ApplicationListener<ContextRefreshedEvent> {
    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        System.out.println(getClass().getSimpleName());

        ApplicationContext application = event.getApplicationContext();
        Map<String, Object> beansMap = application.getBeansOfType(Object.class, false, false);
        System.out.println(beansMap.size());
    }
}
