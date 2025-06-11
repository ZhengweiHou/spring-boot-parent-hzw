package com.hzw.learn.mq.rabbitmq.tutorials.Hello;

import java.io.IOException;
import java.util.Random;
import java.util.Scanner;
import java.util.concurrent.TimeoutException;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

public class Send {
	public static void main(String[] args) throws IOException, TimeoutException {
		
		ConnectionFactory connectionFactory = new ConnectionFactory();
//		ConnectionFactoryConfigurator factoryConf = new ConnectionFactoryConfigurator();
		connectionFactory.setHost("localhost");
//		connectionFactory.setVirtualHost("test");
		connectionFactory.setUsername("admin");
		connectionFactory.setPassword("admin");
		Connection connection = connectionFactory.newConnection();
		Channel channel = connection.createChannel();
		channel.queueDeclare("hzw-hello", false, false, false, null); // 创建队列
//		channel.queueDeclare("hzwhelloqueue1", false, false, false, null);
//		channel.queueDeclare("hzwhelloqueue2", false, false, false, null);
		channel.queueDeclare("hzw-hello1", false, false, false, null); // 创建队列
		channel.queueDeclare("hzw-hello2", false, false, false, null); // 创建队列
		
		Scanner scan = new Scanner(System.in);
		while (true) {
			String message = scan.nextLine();
			message = "time:[" + System.currentTimeMillis() + "]" +message;

			String[] queues = {"hzw-hello", "hzw-hello1", "hzw-hello2"};
			Random rand = new Random();
			int num = rand.nextInt(3);
			channel.basicPublish("", queues[num], null, (message+"."+queues[num]).getBytes()); // 随机发送队列

//			channel.basicPublish("", "hzw-hello", null, message.getBytes()); // 向指定队列发送消息
			System.out.println("[x] sent:" + message);
		}
		
	}
}
