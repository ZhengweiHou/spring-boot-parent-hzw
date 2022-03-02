package com.hzw.learn.springboot.mq.rabbitmq.Tutorials.Workqueues;

import java.io.IOException;
import java.util.Scanner;
import java.util.concurrent.TimeoutException;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.MessageProperties;

public class SendWork {
	public static void main(String[] args) throws IOException, TimeoutException {
		
		ConnectionFactory connectionFactory = new ConnectionFactory();
//		connectionFactory.setHost("192.168.32.131");
		connectionFactory.setHost("localhost");
//		connectionFactory.setVirtualHost("test");
		connectionFactory.setUsername("admin");
		connectionFactory.setPassword("admin");
		Connection connection = connectionFactory.newConnection();
		Channel channel = connection.createChannel();
		channel.queueDeclare("hzw.workqueue", false, false, false, null);
//		创建队列参数说明： 1.队列名 2.是否持久化 3.是否是独占队列 4.是否自动删除 5.其他参数 
		
		Scanner scan = new Scanner(System.in);
		while (true) {
			System.out.println(" [*] input message to send...");
			String message = scan.nextLine();
			
//			channel.basicPublish("", "hzw.workqueue", null, message.getBytes()); 
			channel.basicPublish(
					"", 
					"hzw.workqueue", 
					MessageProperties.PERSISTENT_TEXT_PLAIN, 	//设置消息持久化
					message.getBytes()); 
			System.out.println("[x] sent:" + message);
		}
		
	}
}
