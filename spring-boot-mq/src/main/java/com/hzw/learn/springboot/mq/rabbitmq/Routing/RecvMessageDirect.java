package com.hzw.learn.springboot.mq.rabbitmq.Routing;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

import com.rabbitmq.client.BuiltinExchangeType;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.DeliverCallback;

public class RecvMessageDirect {
	public static void main(String[] args) throws IOException, TimeoutException {
		
		String EXCHANGE_NAME="hzw.Direct_exchange";
		
		if(args.length < 1) {
			args = new String[] {"0","1","2"};
//			args = new String[] {"0"};
	//		args = new String[] {"1"};
	//		args = new String[] {"2"};
		}
		
		 ConnectionFactory connectionFactory = new ConnectionFactory();
		 	connectionFactory.setHost("192.168.32.131");
//			connectionFactory.setVirtualHost("test");
//			connectionFactory.setVirtualHost("/");
			connectionFactory.setUsername("admin");
			connectionFactory.setPassword("admin");
	        Connection connection = connectionFactory.newConnection();
	        Channel channel = connection.createChannel();
	        
//	        channel.exchangeDeclare(EXCHANGE_NAME, BuiltinExchangeType.DIRECT);
	        
//	        String queueName = channel.queueDeclare().getQueue();
	        String queueName = "a123456";
	        
	        String routStr="";
	        
	        for(String routingkey : args) {
	        	channel.queueBind(queueName, EXCHANGE_NAME, routingkey);
	        	routStr += routingkey + ",";
	        }

	        System.out.println(" [*] rout:["+routStr+"] Waiting for messages...");

	        DeliverCallback deliverCallback = (consumerTag, delivery) -> {
	            String message = new String(delivery.getBody(), "UTF-8");
//	            System.out.println(" [x] [" + queueName + "]Received '" + message + "'");
	            System.out.println("[x] routingkey:["+delivery.getEnvelope().getRoutingKey()+"] Received:[" + message + "]");
	        };
	        
	        channel.basicConsume(
	        		queueName, 
	        		true, 			// 关闭autoAsk
	        		deliverCallback, 
	        		consumerTag -> { });
	}
}
