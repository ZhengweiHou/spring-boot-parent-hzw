package com.hzw.learn.springboot.mq.rabbitmq.Tutorials.PublishOrSubscribe;

import java.io.IOException;
import java.util.Scanner;
import java.util.concurrent.TimeoutException;

import com.rabbitmq.client.AMQP.Exchange;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.MessageProperties;

public class EmitMessage {
	public static void main(String[] args) throws IOException, TimeoutException {
		
		ConnectionFactory connectionFactory = new ConnectionFactory();
//		connectionFactory.setHost("192.168.32.131");
		connectionFactory.setHost("localhost");
//		connectionFactory.setVirtualHost("test");
		connectionFactory.setUsername("admin");
		connectionFactory.setPassword("admin");
		Connection connection = connectionFactory.newConnection();
		Channel channel = connection.createChannel();
		
		channel.exchangeDeclare("hzw.exchange", "fanout");
		
		Scanner scan = new Scanner(System.in);
		while (true) {
			System.out.println(" [*] input message to send...");
			String message = scan.nextLine();
			
			channel.basicPublish(
					"hzw.exchange", 
					"", 
					MessageProperties.PERSISTENT_TEXT_PLAIN, 	//设置消息持久化
					message.getBytes()); 
			System.out.println("[x] sent:" + message);
		}
		
	}
}
