package com.hzw.learn.springboot.mq.rabbitmq.Tutorials.Hello;

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
//			connectionFactory.setVirtualHost("test");
			connectionFactory.setUsername("admin");
			connectionFactory.setPassword("admin");
//		 	connectionFactory.setHost("10.250.1.65");
//			connectionFactory.setVirtualHost("vhost_hbyh");
//			connectionFactory.setUsername("admin");
//			connectionFactory.setPassword("admin");
	        Connection connection = connectionFactory.newConnection();
	        Channel channel = connection.createChannel();
	        channel.basicQos(1, true);

//	        channel.queueDeclare("hzw-hello", false, false, false, null);
	        System.out.println(" [*] Waiting for messages...");

	        DeliverCallback deliverCallback = (consumerTag, delivery) -> {
	            String message = new String(delivery.getBody(), "UTF-8");
	            System.out.println("xxxxxx");
	            try {
					Thread.sleep(3000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
	            System.out.println(" [x] Received '" + message + "'");
	        };
	        channel.basicConsume("hzw-hello", true, deliverCallback, consumerTag -> { });
	}
}
