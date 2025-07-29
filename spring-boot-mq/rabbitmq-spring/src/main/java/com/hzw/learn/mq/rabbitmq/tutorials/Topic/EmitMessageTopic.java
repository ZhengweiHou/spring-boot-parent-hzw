package com.hzw.learn.mq.rabbitmq.tutorials.Topic;

import java.io.IOException;
import java.util.Scanner;
import java.util.concurrent.TimeoutException;

import com.rabbitmq.client.BuiltinExchangeType;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.MessageProperties;

public class EmitMessageTopic {
	public static void main(String[] args) throws IOException, TimeoutException {

		String EXCHANGE_NAME="hzw.Topic_exchange";

		ConnectionFactory connectionFactory = new ConnectionFactory();
//		connectionFactory.setHost("192.168.32.131");
		connectionFactory.setHost("localhost");
		connectionFactory.setUsername("admin");
		connectionFactory.setPassword("admin");
		connectionFactory.setVirtualHost("test");
		Connection connection = connectionFactory.newConnection();
		Channel channel = connection.createChannel();

		channel.exchangeDeclare(EXCHANGE_NAME,BuiltinExchangeType.TOPIC);	// ecchangeType = TOPIC

		Scanner scan = new Scanner(System.in);
		while (true) {
			System.out.println(" [*] input message to send...");

			String message = scan.nextLine();

			String routingKey = message.length() > 0 && message.contains("=")
					?
						(String) message.substring(0, message.indexOf("="))
					:
						"0";
			message =
					message.length() > 0 && message.contains("=")
					?
						(String) message.substring(message.indexOf("=")+1,message.length())
					:
						"default message!!";


			channel.basicPublish(
					EXCHANGE_NAME,
					routingKey,
					MessageProperties.PERSISTENT_TEXT_PLAIN, 	//设置消息持久化
					message.getBytes());
			System.out.println("[x] rout:["+routingKey+"] sent:" + message);
		}

	}
}
