package com.hzw.learn.springboot.mq.rabbitmq.Hello;

import java.io.IOException;
import java.util.Scanner;
import java.util.concurrent.TimeoutException;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

public class Send {
	public static void main(String[] args) throws IOException, TimeoutException {
		
		ConnectionFactory connectionFactory = new ConnectionFactory();
//		ConnectionFactoryConfigurator factoryConf = new ConnectionFactoryConfigurator();
		connectionFactory.setHost("192.168.32.131");
		connectionFactory.setVirtualHost("hzwVhost");
		connectionFactory.setUsername("admin");
		connectionFactory.setPassword("admin");
		Connection connection = connectionFactory.newConnection();
		Channel channel = connection.createChannel();
		channel.queueDeclare("hzwhelloqueue", false, false, false, null); // 创建队列
		channel.queueDeclare("hzwhelloqueue1", false, false, false, null);
		channel.queueDeclare("hzwhelloqueue2", false, false, false, null);
		
		Scanner scan = new Scanner(System.in);
		while (true) {
			String message = scan.nextLine();
			message = "time:[" + System.currentTimeMillis() + "]" +message;
			
			channel.basicPublish("", "hzwhelloqueue", null, message.getBytes()); // 向指定队列发送消息
			System.out.println("[x] sent:" + message);
		}
		
	}
}
