package com.hzw.learn.springboot.springbase.ApplicationEventTest.customEvents;

import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

/**
 * @ClassName HzwEventListener
 * @Description TODO
 * @Author houzw
 * @Date 2024/6/4
 **/
@Component
public class HzwEventListener implements ApplicationListener<HzwEvent> {
    @Override
    public void onApplicationEvent(HzwEvent event) {
        System.out.println(getClass().getSimpleName() + "   msg:" + event.msg);
    }
}
