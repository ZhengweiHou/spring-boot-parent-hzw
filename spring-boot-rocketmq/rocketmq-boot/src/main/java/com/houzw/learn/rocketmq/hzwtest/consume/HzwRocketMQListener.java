package com.houzw.learn.rocketmq.hzwtest.consume;

import org.apache.rocketmq.spring.annotation.RocketMQMessageListener;
import org.apache.rocketmq.spring.core.RocketMQListener;
import org.springframework.stereotype.Service;

/**
 * @ClassName StringTransactionalConsumer
 * @Description TODO
 * @Author houzw
 * @Date 2023/3/13
 **/
@Service
@RocketMQMessageListener(topic = "hzw_trans_topic", consumerGroup = "hzwTest")
public class HzwRocketMQListener implements RocketMQListener<String> {
    @Override
    public void onMessage(String message) {
        System.out.printf("------- 接收到事務消息: %s \n", message);
    }
}
