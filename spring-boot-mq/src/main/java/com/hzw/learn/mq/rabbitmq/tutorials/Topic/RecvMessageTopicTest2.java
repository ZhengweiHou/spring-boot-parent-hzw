package com.hzw.learn.mq.rabbitmq.tutorials.Topic;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.DeliverCallback;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

public class RecvMessageTopicTest2 {
	public static void main(String[] args) throws IOException, TimeoutException {
		
		String EXCHANGE_NAME="hzw.Topic_exchange";
		

		 ConnectionFactory connectionFactory = new ConnectionFactory();
			connectionFactory.setHost("localhost");
			connectionFactory.setUsername("admin");
			connectionFactory.setPassword("admin");
			connectionFactory.setVirtualHost("test");
	        Connection connection = connectionFactory.newConnection();
	        Channel channel = connection.createChannel();


	     //   String queueName = channel.queueDeclare().getQueue();
			String queueName = channel.queueDeclare("recv_1111",true,false,false,null).getQueue();

		channel.queueBind(queueName, EXCHANGE_NAME, "#");


	        DeliverCallback deliverCallback = (consumerTag, delivery) -> {
	            String message = new String(delivery.getBody(), "UTF-8");
	            System.out.println("[x] routingkey:["+delivery.getEnvelope().getRoutingKey()+"] Received:[" + message + "]");
	        };
	        
	        channel.basicConsume(
	        		queueName, 
	        		true, 			// 关闭autoAsk
	        		deliverCallback, 
	        		consumerTag -> { });
	}
}
