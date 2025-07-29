package com.hzw.learn.service;

import com.hzw.learn.config.RabbitMQConfig;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageProperties;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.SimpleMessageConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MessageProducer {

    @Autowired
    // private AmqpTemplate rabbitTemplate;
    private RabbitTemplate rabbitTemplate;

    public void sendMessage(String message) {
        rabbitTemplate.convertAndSend(RabbitMQConfig.QUEUE_NAME, message);
        System.out.println("发送消息: " + message);
    }

    public String sendAndReceive(String message) {
        System.out.println("P SEND: " + message);
       
        // rabbitTemplate.setCorrelationKey("hhzzww");
        Object response = rabbitTemplate.convertSendAndReceive(
            RabbitMQConfig.QUEUE_NAME,
            message,
            m -> {
                // m.getMessageProperties().setCorrelationId("hhzzww");
                return m;
            });
            
        System.out.println("P RECEIVE: " + response);
        return response != null ? response.toString() : null;
    }

    public String sendAndReceiveMessage(String mstr) {

        rabbitTemplate.setCorrelationKey("hzw-correlationKey");
        // rabbitTemplate.setFast


        System.out.println("P SEND: " + mstr);
        MessageProperties mp = new MessageProperties();
        Message reqm = new Message(mstr.getBytes(), mp);

        Message repm = rabbitTemplate.sendAndReceive(
            RabbitMQConfig.QUEUE_NAME,
            reqm
        );

        String rstr = "";
        if (repm != null) {
            Object robj = new SimpleMessageConverter().fromMessage(repm);
            rstr = new String((byte[])robj);
            System.out.println("P RECEIVE: " + rstr);
        }else{
            System.out.println("P RECEIVE: null");
            rstr = "null";
        }

        return rstr;
    }
}
