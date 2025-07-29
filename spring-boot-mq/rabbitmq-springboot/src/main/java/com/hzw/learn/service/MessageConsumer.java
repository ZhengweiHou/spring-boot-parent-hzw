package com.hzw.learn.service;

import com.google.gson.Gson;
import com.hzw.learn.config.RabbitMQConfig;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.support.converter.SimpleMessageConverter;

import java.util.Map;

import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageProperties;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

@Service
public class MessageConsumer {

    private final SimpleMessageConverter messageConverter = new SimpleMessageConverter();

    @RabbitListener(queues = RabbitMQConfig.QUEUE_NAME)
    public Message receiveMessage(@Payload String msg, Message message) {

        // 解析消息
        Object fmsg = messageConverter.fromMessage(message);
        String fmsgstr = new String((byte[])fmsg);
        System.out.println("==C RECEIVE: " + fmsgstr);

        // 解析消息头
        Map<String, Object> mheaders = message.getMessageProperties().getHeaders();
        String mhjson = new Gson().toJson(mheaders);
        System.out.println("mhjson: " + mhjson);

        String replyTo = message.getMessageProperties().getReplyTo();
        System.out.println("replyTo: " + replyTo);

       
        MessageProperties mp = new MessageProperties();
        // 消息头原样返回，其中包含correlationKey:[correlationValue], Producer端若设置了CorrelationKey，需要此值做消息溯源
        mp.getHeaders().putAll(message.getMessageProperties().getHeaders());
        mp.getHeaders().put("IS_RPC_REPLEY", true);
        
        String response = "已处理: " + fmsgstr;
        System.out.println("==C SEND: " + response);
        return new Message(response.getBytes(), mp);
    }
}
