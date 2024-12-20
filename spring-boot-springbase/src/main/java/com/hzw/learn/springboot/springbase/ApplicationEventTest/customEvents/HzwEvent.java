package com.hzw.learn.springboot.springbase.ApplicationEventTest.customEvents;

import org.springframework.context.ApplicationEvent;

/**
 * @ClassName HzwEvent
 * @Description TODO
 * @Author houzw
 * @Date 2024/6/4
 **/
public class HzwEvent extends ApplicationEvent {
    public String msg;
    /**
     * Create a new ApplicationEvent.
     * @param source the object on which the event initially occurred (never {@code null})
     */
    public HzwEvent(Object source, String msg) {
        super(source);
        this.msg = msg;
    }
}
