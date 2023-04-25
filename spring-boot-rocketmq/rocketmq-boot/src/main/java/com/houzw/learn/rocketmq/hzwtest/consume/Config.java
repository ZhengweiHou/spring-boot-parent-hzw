package com.houzw.learn.rocketmq.hzwtest.consume;

import org.apache.rocketmq.client.impl.consumer.DefaultMQPushConsumerImpl;
import org.apache.rocketmq.spring.support.DefaultRocketMQListenerContainer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationStartedEvent;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @ClassName Config
 * @Description TODO
 * @Author houzw
 * @Date 2023/3/14
 **/
@Configuration
public class Config  implements ApplicationListener<ApplicationStartedEvent> {

    @Autowired
    private ApplicationContext applicationContext;

//    @Bean
//    public void setHook(DefaultRocketMQListenerContainer lc){
//        DefaultMQPushConsumerImpl consumer = lc.getConsumer().getDefaultMQPushConsumerImpl();
//        consumer.registerConsumeMessageHook(new HzwConsunmeMessageHook());
//    }

    @Override
    public void onApplicationEvent(ApplicationStartedEvent applicationStartedEvent) {
        applicationContext.getBeansOfType(DefaultRocketMQListenerContainer.class, false, false)
                .values().forEach(con -> {
                    DefaultMQPushConsumerImpl consumer = con.getConsumer().getDefaultMQPushConsumerImpl();
                    consumer.registerConsumeMessageHook(new HzwConsunmeMessageHook());
                });
    }
}
