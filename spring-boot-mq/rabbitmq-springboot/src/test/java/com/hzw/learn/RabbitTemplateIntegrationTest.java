package com.hzw.learn;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageBuilder;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class RabbitTemplateIntegrationTest {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Test
    public void testSendAndReceive() throws InterruptedException {
        System.out.println("testSendAndReceive");
        // 测试1: 基本发送接收
        String queueName = "test.queue";
        String message = "Test message";
        
        Message mqMessage = MessageBuilder
            .withBody(message.getBytes())
            .build();
            
        rabbitTemplate.setCorrelationKey("spring_reply_correlation");
        Message reply = rabbitTemplate.sendAndReceive(queueName, mqMessage);
        assertNotNull("Reply should not be null", reply);
        
        // 测试2: 模拟多实例并发
        Thread thread1 = new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                Message msg = MessageBuilder
                    .withBody(("Thread1-" + i).getBytes())
                    .build();
                rabbitTemplate.setCorrelationKey("spring_reply_correlation");
                Message replyMsg = rabbitTemplate.sendAndReceive(queueName, msg);
                System.out.println("Thread1 received: " + new String(replyMsg.getBody()));
            }
        });
        
        Thread thread2 = new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                Message msg = MessageBuilder
                    .withBody(("Thread2-" + i).getBytes())
                    .build();
                rabbitTemplate.setCorrelationKey("spring_reply_correlation");
                Message replyMsg = rabbitTemplate.sendAndReceive(queueName, msg);
                System.out.println("Thread2 received: " + new String(replyMsg.getBody()));
            }
        });
        
        thread1.start();
        thread2.start();
        thread1.join();
        thread2.join();
    }
}
