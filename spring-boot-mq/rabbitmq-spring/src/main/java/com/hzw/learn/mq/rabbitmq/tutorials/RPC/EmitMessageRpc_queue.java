package com.hzw.learn.mq.rabbitmq.tutorials.RPC;

import java.io.IOException;
import java.util.Scanner;
import java.util.UUID;
import java.util.concurrent.TimeoutException;

import com.rabbitmq.client.AMQP.BasicProperties;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.DeliverCallback;

public class EmitMessageRpc_queue {
	public static void main(String[] args) throws IOException, TimeoutException {
		
		String QUEUE_NAME="hzw.RPC_queue";
		
		ConnectionFactory connectionFactory = new ConnectionFactory();
//		connectionFactory.setHost("192.168.32.131");
		connectionFactory.setVirtualHost("test");
		connectionFactory.setUsername("admin");
		connectionFactory.setPassword("admin");
		connectionFactory.setHost("localhost");
//		connectionFactory.setVirtualHost("/dev");
//		connectionFactory.setUsername("guest");
//		connectionFactory.setPassword("guest");
		Connection connection = connectionFactory.newConnection();
		Channel channel = connection.createChannel();
		

		Scanner scan = new Scanner(System.in);
		while (true) {
			System.out.println(" [*] input message to send...");
			
//			message eg： [routkey]=[message]
			String message = scan.nextLine();
			
			// 创建一个随机队列，并获取队列名
			String callbackQueueName = channel.queueDeclare().getQueue();
			
			// 生成"serverId"
			String correlationId = UUID.randomUUID().toString();
			
			// 构建mq调用参数
			BasicProperties props = 
					new BasicProperties.Builder()
					.correlationId(correlationId)	// 设置每个请求的唯一值-类似于交易码，双方可以自定义使用该值确认同一交易
					.replyTo(callbackQueueName)		// 指定回调队列名
					.build();
			
			System.out.println("[x] -send- correlationId:["+correlationId+"] sent:" + message);
			
			channel.basicPublish(
					"", 				// 没指定exchange则默认使用Exchange:(AMQP default)
					QUEUE_NAME, 		// 队列名作为routingKey
					props,				// 使用构建的参数，包含回调队列名
					message.getBytes()); 
			
			/*
			 ======通过回调队列获取返回信息========
			 */
			
	        DeliverCallback deliverCallback = (consumerTag, delivery) -> {
	            String callbackMsg = new String(delivery.getBody(), "UTF-8");
	            String call_correlationId = delivery.getProperties().getCorrelationId();
	            
	            System.out.println("[+] -back- correlationId:["+call_correlationId+"] Received:[" + callbackMsg + "]");
	            channel.basicCancel(consumerTag);
	        };	
	        
	        String consumerTagFromServer = channel.basicConsume(
	        		callbackQueueName, 
	        		true, 
	        		deliverCallback, 
	        		cancelCallback -> {});
	        
//	        channel.basicCancel(consumerTagFromServer);
	        
		}
		
	}
}
