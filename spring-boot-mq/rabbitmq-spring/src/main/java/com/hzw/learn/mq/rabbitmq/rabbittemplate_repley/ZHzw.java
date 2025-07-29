package com.hzw.learn.mq.rabbitmq.rabbittemplate_repley;

import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory.CacheMode;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;

public class ZHzw {
   
    public static final String RPC_QUEUE_NAME="hzw.RPC_byspringamqp_queue";

    public static ConnectionFactory newConnectionFactory(){
        CachingConnectionFactory connectionFactory = new CachingConnectionFactory();
        connectionFactory.setAddresses("localhost");
        connectionFactory.setUsername("admin");
        connectionFactory.setPassword("admin");
        connectionFactory.setVirtualHost("hzw");
        connectionFactory.setCacheMode(CacheMode.CHANNEL);
        return connectionFactory;
    }
}
