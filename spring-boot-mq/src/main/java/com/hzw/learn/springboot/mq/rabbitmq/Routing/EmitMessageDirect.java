package com.hzw.learn.springboot.mq.rabbitmq.Routing;

import java.io.IOException;
import java.util.Scanner;
import java.util.concurrent.TimeoutException;

import com.rabbitmq.client.AMQP.Exchange;
import com.rabbitmq.client.BuiltinExchangeType;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.MessageProperties;

public class EmitMessageDirect {
	public static void main(String[] args) throws IOException, TimeoutException {
		
		String EXCHANGE_NAME="hzw.Direct_exchange";
		
		ConnectionFactory connectionFactory = new ConnectionFactory();
		connectionFactory.setHost("192.168.32.131");
//		connectionFactory.setVirtualHost("test");
		connectionFactory.setUsername("admin");
		connectionFactory.setPassword("admin");
		Connection connection = connectionFactory.newConnection();
		Channel channel = connection.createChannel();
		channel.queueDeclare("a123456", false, false, false, null);
		channel.exchangeDeclare(EXCHANGE_NAME,BuiltinExchangeType.DIRECT);	// ecchangeType = direct
		
		Scanner scan = new Scanner(System.in);
		while (true) {
			System.out.println(" [*] input message to send...");
			String message = scan.nextLine();
			
			String routingKey = message.length() > 0 ? message.substring(0, 1) : "0";
			
			message = message.length() > 0 ? message : "default message!!";
			
			
			channel.basicPublish(
					EXCHANGE_NAME, 
					routingKey, 
					MessageProperties.PERSISTENT_TEXT_PLAIN, 	//设置消息持久化
					message.getBytes()); 
			System.out.println("[x] rout:["+routingKey+"] sent:" + message);
		}
		
	}
}
