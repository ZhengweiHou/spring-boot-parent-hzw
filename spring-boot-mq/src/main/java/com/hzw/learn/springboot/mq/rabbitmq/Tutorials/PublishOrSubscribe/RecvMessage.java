package com.hzw.learn.springboot.mq.rabbitmq.Tutorials.PublishOrSubscribe;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.DeliverCallback;

public class RecvMessage {
	public static void main(String[] args) throws IOException, TimeoutException {
		 ConnectionFactory connectionFactory = new ConnectionFactory();
//		 	connectionFactory.setHost("192.168.32.131");
		 	connectionFactory.setHost("localhost");
//			connectionFactory.setVirtualHost("test");
//			connectionFactory.setVirtualHost("/");
			connectionFactory.setUsername("admin");
			connectionFactory.setPassword("admin");
	        Connection connection = connectionFactory.newConnection();
	        Channel channel = connection.createChannel();
	        
//	        channel.exchangeDeclare("hzw.exchange", "fanout");
	        
	        String queueName = channel.queueDeclare().getQueue();
	        channel.queueBind(queueName, "hzw.exchange", "");
	        
	        System.out.println(" [*] Waiting for messages...");

	        DeliverCallback deliverCallback = (consumerTag, delivery) -> {
	            String message = new String(delivery.getBody(), "UTF-8");
	            System.out.println(" [x] [" + queueName + "]Received '" + message + "'");
	        };
	        
	        channel.basicConsume(
	        		queueName, 
	        		true, 			// 关闭autoAsk
	        		deliverCallback, 
	        		consumerTag -> { });
	}
}
