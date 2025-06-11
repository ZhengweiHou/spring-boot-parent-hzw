package com.hzw.learn.mq.rabbitmq.tutorials.RPC;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

import com.rabbitmq.client.AMQP.BasicProperties;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.DeliverCallback;

public class RecvMessageRpc_exchange {
	public static void main(String[] args) throws IOException, TimeoutException {
		
		String EXCHANGE_NAME="hzw.Rpc_exchange";
		
		if(args.length < 1) {
			args = new String[] {"#"}; // 接受所有消息
//			args = new String[] {"0"};
//			args = new String[] {"1.*"};
//			args = new String[] {"*.1"};
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
	        
	        String queueName = channel.queueDeclare().getQueue();
	        
	        String routStr="";
	        
	        for(String routingkey : args) {
	        	channel.queueBind(queueName, EXCHANGE_NAME, routingkey);
	        	routStr += routingkey + ",";
	        }

	        System.out.println(" [*] rout:["+routStr+"] Waiting for messages...");

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
	        		queueName, 
	        		true, 			// 关闭autoAsk
	        		deliverCallback, 	// 回调操作里再调用了回调队列
	        		cancelCallback -> { });
	}
}
