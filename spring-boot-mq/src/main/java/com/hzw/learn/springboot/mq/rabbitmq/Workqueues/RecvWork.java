package com.hzw.learn.springboot.mq.rabbitmq.Workqueues;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.DeliverCallback;

public class RecvWork {
	public static void main(String[] args) throws IOException, TimeoutException {
		 ConnectionFactory connectionFactory = new ConnectionFactory();
		 	connectionFactory.setHost("192.168.32.131");
			connectionFactory.setVirtualHost("test");
			connectionFactory.setUsername("admin");
			connectionFactory.setPassword("admin");
	        Connection connection = connectionFactory.newConnection();
	        Channel channel = connection.createChannel();
	        channel.basicQos(1); // 限制每次请求一条数据
	        
	        
	        System.out.println(" [*] Waiting for messages...");

	        DeliverCallback deliverCallback = (consumerTag, delivery) -> {
	            String message = new String(delivery.getBody(), "UTF-8");
	            System.out.println(" [x] [" + System.currentTimeMillis() + "]Received '" + message + "'");
	            System.out.println(delivery.getEnvelope().getDeliveryTag());
	            try {
					doWork(message);
				} catch (InterruptedException e) {} 
	            finally {
					System.out.println(" [x] [" + System.currentTimeMillis() + "]Done '" + message + "'");
					channel.basicAck(delivery.getEnvelope().getDeliveryTag(), false); // 自定义响应
				}
	        };
	        
	        channel.basicConsume(
	        		"hzw.workqueue", 
	        		false, 			// 关闭autoAsk
	        		deliverCallback, 
	        		consumerTag -> { });
	}
	
	private static void doWork(String message) throws InterruptedException {
		for(char ch : message.toCharArray()) {
			if(ch == '.') Thread.sleep(1000l);
		}
	}
}
