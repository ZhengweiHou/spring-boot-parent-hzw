package com.hzw.learn.mq.rabbitmq.rabbittemplate_repley;

import org.junit.Test;
import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory.CacheMode;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
import org.springframework.amqp.rabbit.listener.adapter.MessageListenerAdapter;
import org.springframework.amqp.support.converter.SimpleMessageConverter;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

public class RecvMessage {
    public static void main(String[] args) throws IOException, TimeoutException {

        ConnectionFactory connectionFactory = ZHzw.newConnectionFactory() ;
        RabbitTemplate mqTemplate = new RabbitTemplate(connectionFactory);

        SimpleMessageConverter msgconvert = new SimpleMessageConverter();
        Queue rpcqueue = new Queue(ZHzw.RPC_QUEUE_NAME);

        // 尝试声明队列
        AmqpAdmin amqpAdmin = new RabbitAdmin(connectionFactory);
        amqpAdmin.declareQueue(rpcqueue);

        MessageListenerAdapter mla = new MessageListenerAdapter();
        mla.setDelegate(new MessageListener() {
            public void onMessage(Message message) {
                String replyto = message.getMessageProperties().getReplyTo();

                System.out.println("C recv msg: " + new String(message.getBody()));

                String replyTo = message.getMessageProperties().getReplyTo();
                System.out.println("replyTo: " + replyTo);

                String replyMsg = "reply msg > " + new String(message.getBody());

                MessageProperties mp = new MessageProperties();
                mp.getHeaders().putAll(message.getMessageProperties().getHeaders());
                mp.getHeaders().put("HZW_IS_REPLY", true);

                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }

                Message rmsg = msgconvert.toMessage(replyMsg, mp);
                System.out.println("C send msg: " + replyMsg);
                mqTemplate.send(replyto, rmsg);
                // mqTemplate.send("hzw.RPC_byspringamqp_reply_queue", rmsg);

                System.out.println("==========");
                // try {
                //     Thread.sleep(1000);
                // } catch (InterruptedException e) {
                //     e.printStackTrace();
                // }
            }
        });

        // 每一服务都是一个ListenerContainer
        SimpleMessageListenerContainer messageListnerCon = new SimpleMessageListenerContainer();
        messageListnerCon.setConnectionFactory(connectionFactory);
        messageListnerCon.setMessageListener(mla); // 指定消息监听器
        messageListnerCon.setQueues(rpcqueue);
        messageListnerCon.setConcurrency("3");
        messageListnerCon.start();
    }
}
