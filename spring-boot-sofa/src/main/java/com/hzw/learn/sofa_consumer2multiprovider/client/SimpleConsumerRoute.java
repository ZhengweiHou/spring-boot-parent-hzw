package com.hzw.learn.sofa_consumer2multiprovider.client;

/**
 * @ClassName SimpleConsumerRoute
 * @Description TODO
 * @Author houzw
 * @Date 2022/9/13
 **/
public class SimpleConsumerRoute implements ConsumerRoute{
    @Override
    public String getRouteKey(Object o) {
        return o instanceof String ? ((String) o) : null;
    }
}
