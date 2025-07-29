package com.hzw.learn.mq.rabbitmq.rabbittemplate_repley;

import java.util.Scanner;

import org.springframework.amqp.AmqpException;
import org.springframework.amqp.core.AmqpAdmin;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessagePostProcessor;
import org.springframework.amqp.core.MessageProperties;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory.CacheMode;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;


public class EmitMessageRepleyDirect {
	public static void main(String[] args) throws Exception {

        // == 创建连接工厂 ==
        ConnectionFactory connectionFactory = ZHzw.newConnectionFactory() ;
        // == 创建Template ==
        RabbitTemplate mqTemplate = new RabbitTemplate(connectionFactory);
        mqTemplate.setReplyTimeout(30 * 1000);


        // 设置correlationKey，用于匹配reply消息
        mqTemplate.setCorrelationKey("hzw_reply_correlation");

        // 创建CountDownLatch和线程池
        int threadCount = 1;
        int messagesPerThread = 1;
        CountDownLatch latch = new CountDownLatch(threadCount * messagesPerThread);
        ExecutorService executor = Executors.newFixedThreadPool(threadCount);
        
        // 提交发送任务
        for (int i = 0; i < threadCount; i++) {
            executor.submit(() -> {
                for (int j = 0; j < messagesPerThread; j++) {
                    try {
                        String message = "Hello RabbitMQ from " + Thread.currentThread().getName();
                        System.out.println("P send msg: " + message);
                        Object responseMsg = mqTemplate.convertSendAndReceive(
                            ZHzw.RPC_QUEUE_NAME,
                            message, 
                            m -> {
                                MessageProperties mp = m.getMessageProperties();
                                return m;
                            }
                        );
                        System.out.println("P recv msg: " + responseMsg);
                        // Thread.sleep(1000);
                    } catch (Exception e) {
                        e.printStackTrace();
                    } finally {
                        latch.countDown();
                    }
                }
            });
        }
       
        // 等待所有消息发送完成
        latch.await();
        // 关闭线程池
        executor.shutdown();
        executor.awaitTermination(1, TimeUnit.MINUTES);
        System.out.println("所有消息发送完成");

	}
}
