package com.hzw.learn;

import com.hzw.learn.service.MessageProducer;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class RabbitmqDemoRunner implements CommandLineRunner {

    private final MessageProducer messageProducer;

    public RabbitmqDemoRunner(MessageProducer messageProducer) {
        this.messageProducer = messageProducer;
    }

    @Override
    public void run(String... args) throws Exception {

        for (int i = 0; i < 500; i++) {
            messageProducer.sendAndReceiveMessage("Hello RabbitMQ! " + i);
            Thread.sleep(1000); // 等待消费者处理
        }
        //messageProducer.sendMessage("Hello RabbitMQ! " + i);
        // messageProducer.sendAndReceive("Hello RabbitMQ! " + i);
        // messageProducer.sendAndReceive("Hello RabbitMQ! ");
        // messageProducer.sendAndReceiveMessage("Hello RabbitMQ! ");
        System.exit(0); // 发送后退出
    }
}
