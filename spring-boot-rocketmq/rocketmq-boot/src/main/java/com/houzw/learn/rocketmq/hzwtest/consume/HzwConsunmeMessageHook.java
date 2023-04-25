package com.houzw.learn.rocketmq.hzwtest.consume;

import org.apache.rocketmq.client.hook.ConsumeMessageContext;
import org.apache.rocketmq.client.hook.ConsumeMessageHook;
import org.springframework.stereotype.Component;

/**
 * @ClassName HzwConsunmeMessageHook
 * @Description TODO
 * @Author houzw
 * @Date 2023/3/14
 **/
@Component
public class HzwConsunmeMessageHook implements ConsumeMessageHook {
    @Override
    public String hookName() {
        return this.getClass().getSimpleName();
    }

    @Override
    public void consumeMessageBefore(ConsumeMessageContext context) {
        System.out.println("consumeMessageBefore..");
    }

    @Override
    public void consumeMessageAfter(ConsumeMessageContext context) {
        System.out.println("consumeMessageAfter..");
    }
}
