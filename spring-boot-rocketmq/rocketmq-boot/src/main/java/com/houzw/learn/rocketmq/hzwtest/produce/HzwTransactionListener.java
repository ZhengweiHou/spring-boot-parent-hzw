package com.houzw.learn.rocketmq.hzwtest.produce;

import com.google.gson.Gson;
import org.apache.rocketmq.spring.annotation.RocketMQTransactionListener;
import org.apache.rocketmq.spring.core.RocketMQLocalTransactionListener;
import org.apache.rocketmq.spring.core.RocketMQLocalTransactionState;
import org.apache.rocketmq.spring.core.RocketMQTemplate;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.messaging.Message;
import org.springframework.stereotype.Component;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @ClassName HzwTransactionListener
 * @Description TODO
 * @Author houzw
 * @Date 2023/3/13
 **/
@Component
@RocketMQTransactionListener(corePoolSize = 5, maximumPoolSize = 20)
@ConditionalOnClass({RocketMQTemplate.class})
public class HzwTransactionListener implements RocketMQLocalTransactionListener {
    private AtomicInteger transactionIndex = new AtomicInteger(0);

    private ConcurrentHashMap<String, Integer> localTrans = new ConcurrentHashMap<>();
    @Override
    public RocketMQLocalTransactionState executeLocalTransaction(Message msg, Object arg) {
        System.out.printf("executeLocalTransaction %s%n", new Gson().toJson(msg.getPayload()));

        return RocketMQLocalTransactionState.UNKNOWN;
    }

    @Override
    public RocketMQLocalTransactionState checkLocalTransaction(Message msg) {
        System.out.printf("checkLocalTransaction %s%n", new Gson().toJson(msg.getPayload()));

        return RocketMQLocalTransactionState.COMMIT;
    }
}
