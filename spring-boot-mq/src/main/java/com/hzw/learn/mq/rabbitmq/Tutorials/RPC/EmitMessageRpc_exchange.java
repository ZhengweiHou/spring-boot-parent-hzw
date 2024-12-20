package com.hzw.learn.kafkatest.mq.rabbitmq.Tutorials.RPC;

import java.io.IOException;
import java.util.Scanner;
import java.util.UUID;
import java.util.concurrent.TimeoutException;

import com.rabbitmq.client.AMQP.BasicProperties;
import com.rabbitmq.client.BuiltinExchangeType;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.DeliverCallback;

public class EmitMessageRpc_exchange {
	public static void main(String[] args) throws IOException, TimeoutException {
		
		String EXCHANGE_NAME="hzw.Rpc_exchange";
		
		ConnectionFactory connectionFactory = new ConnectionFactory();
		connectionFactory.setHost("192.168.32.131");
//		connectionFactory.setVirtualHost("test");
		connectionFactory.setUsername("admin");
		connectionFactory.setPassword("admin");
		Connection connection = connectionFactory.newConnection();
		Channel channel = connection.createChannel();
		
		channel.exchangeDeclare(EXCHANGE_NAME,BuiltinExchangeType.TOPIC);	// ecchangeType = TOPIC
		
		channel.basicQos(1); // maximum number of messages that the server will deliver, 0 if unlimited
		
		Scanner scan = new Scanner(System.in);
		while (true) {
			System.out.println(" [*] input message to send...");
			
//			message eg： [routkey]=[message]
			String message = scan.nextLine();
			
			String routingKey = 
					message.length() > 0 && message.contains("=") 
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
			
			// 创建一个随机队列，并获取队列名
			String callbackQueueName = channel.queueDeclare().getQueue();
			
			// 生成"serverId"
			String correlationId = UUID.randomUUID().toString();
			
			// 构建mq调用参数
			BasicProperties props = 
					new BasicProperties.Builder()
					.correlationId(correlationId)	// 设置每个请求的唯一值-server
					.replyTo(callbackQueueName)		// 指定回调队列名
					.build();
			
			System.out.println("[x] -send- correlationId:["+correlationId+"] rout:["+routingKey+"] sent:" + message);
			
			channel.basicPublish(
					EXCHANGE_NAME, 
					routingKey, 
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
