package com.hzw.learn.kafkatest.mq.rabbitmq.Tutorials.RPC;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

import com.rabbitmq.client.AMQP.BasicProperties;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.DeliverCallback;

public class RecvMessageRpc_queue {
	public static void main(String[] args) throws IOException, TimeoutException {
		
		String QUEUE_NAME="hzw.RPC_queue";
		
		 ConnectionFactory connectionFactory = new ConnectionFactory();
//		 	connectionFactory.setHost("192.168.32.131");
			connectionFactory.setVirtualHost("test");
//			connectionFactory.setVirtualHost("/");
			connectionFactory.setUsername("admin");
			connectionFactory.setPassword("admin");
			connectionFactory.setHost("localhost");
//			connectionFactory.setVirtualHost("/dev");
//			connectionFactory.setUsername("guest");
//			connectionFactory.setPassword("guest");
	        Connection connection = connectionFactory.newConnection();
	        Channel channel = connection.createChannel();
	        
	        // 服务提供者负责创建队列
			channel.queueDeclare(QUEUE_NAME, false, false, false, null);
			// 清空队列中的内容
			channel.queuePurge(QUEUE_NAME);
	        
			channel.basicQos(1); // maximum number of messages that the server will deliver, 0 if unlimited
	        
	        System.out.println(" [*] queue:["+QUEUE_NAME+"]Waiting for messages...");

	        
	        
	        //===================定义回调类======================
	        DeliverCallback deliverCallback = (consumerTag, delivery) -> {
	        	
	        	BasicProperties replyProps = new BasicProperties
	        			.Builder()
	        			.correlationId(delivery.getProperties().getCorrelationId())
	        			.build();
	        	
	        	// 获取请求的serverId
	        	String correlationId    = delivery.getProperties().getCorrelationId();
	        	
	        	// 获取回调队列名
	        	String replyToQueueName = delivery.getProperties().getReplyTo();
	        	
	        	String response = "";
	        	
	        	try {
	        		// 此处进行业务处理....
		            String msg = new String(delivery.getBody(), "UTF-8");
		        	
		            // 模拟处理时间
		            Thread.sleep(1000l);
		            
		            System.out.println("[x] correlationId:["+correlationId+"] Received:[" + msg + "]");
		        	
		        	response = "hello , we received msg, I'm " + Thread.currentThread().getId();
	        	} catch (Exception e) {
					e.printStackTrace();
				} finally {
	        		channel.basicPublish("", replyToQueueName, replyProps, response.getBytes("UTF-8"));
	        		
	        		// ???? 此处开启后一次请求后，channel就会失效，mq上会解除绑定
//	        		channel.basicAck(delivery.getEnvelope().getDeliveryTag(), false);
	        	}
	        	
	        };
	        
	        channel.basicConsume(
	        		QUEUE_NAME, 
	        		true, 			// 关闭autoAsk
	        		deliverCallback, 	// 回调操作里再调用了回调队列
	        		cancelCallback -> { });
	}
}
