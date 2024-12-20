package com.hzw.learn.kafkatest.mq.rabbitmq.Tutorials.Topic;

import com.rabbitmq.client.*;

import java.io.IOException;
import java.util.Scanner;
import java.util.concurrent.TimeoutException;

public class EmitMessageTopicTest {
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


		channel.basicPublish(
				EXCHANGE_NAME,
				"1.2.3",
				MessageProperties.PERSISTENT_TEXT_PLAIN, 	//设置消息持久化
				"message".getBytes());


	}
}
