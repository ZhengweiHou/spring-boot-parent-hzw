package com.hzw.learn.springboot.mq.rabbitmq.Hello;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.DeliverCallback;

public class Recv {
	public static void main(String[] args) throws IOException, TimeoutException {
		 ConnectionFactory connectionFactory = new ConnectionFactory();
		 	connectionFactory.setHost("192.168.32.131");
			connectionFactory.setVirtualHost("hzwVhost");
			connectionFactory.setUsername("admin");
			connectionFactory.setPassword("admin");
	        Connection connection = connectionFactory.newConnection();
	        Channel channel = connection.createChannel();

	        channel.queueDeclare("hzwhelloqueue", false, false, false, null);
	        System.out.println(" [*] Waiting for messages...");

	        DeliverCallback deliverCallback = (consumerTag, delivery) -> {
	            String message = new String(delivery.getBody(), "UTF-8");
	            System.out.println(" [x] Received '" + message + "'");
	        };
	        channel.basicConsume("hzwhelloqueue", true, deliverCallback, consumerTag -> { });
	}
}
