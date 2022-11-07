package com.hzw.learn.springboot.mq.rabbitmq.Tutorials.RPC;

import com.rabbitmq.client.ShutdownSignalException;
import org.springframework.amqp.AmqpException;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessagePostProcessor;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.connection.Connection;
import org.springframework.amqp.rabbit.connection.ConnectionListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;

import java.util.Scanner;

public class EmitMessageRpc_BySpringAmqp {
	public static void main(String[] args) throws Exception {

		String QUEUE_NAME="hzw.RPC_byspringamqp_queue";

		CachingConnectionFactory connectionFactory = new CachingConnectionFactory();
//		connectionFactory.setAddresses("localhost:5672");
		connectionFactory.setAddresses("localhost");
		connectionFactory.setUsername("admin");
		connectionFactory.setPassword("admin");
		connectionFactory.setVirtualHost("test");

		// 感知异步消息的成功和失败，如：1.发布到exchange，但没有匹配的目标队列。2.发布到不存在的exchange。
		connectionFactory.addConnectionListener(new ConnectionListener() {
			@Override
			public void onCreate(Connection connection) {
				System.out.println("onCreate:" + connection.getLocalPort());
			}
			@Override
			public void onShutDown(ShutdownSignalException signal){
				System.out.println("=======");
				signal.printStackTrace();
			}
		});


		RabbitTemplate mqTemplate = new RabbitTemplate(connectionFactory);

		Scanner scan = new Scanner(System.in);
		int count=0;
		while (true) {
			System.out.println(" [*] input message to send...");
			String message = scan.nextLine();

			String routingKey = message.length() > 0 && message.contains("=")
					?
					(String) message.substring(0, message.indexOf("="))
					:
					"0";
			message =
					message.length() > 0 && message.contains("=")
							?
							(String) message.substring(message.indexOf("=") + 1, message.length())
							:
							"default message!!";
			message+=++count;

			Object responseMsg = mqTemplate.convertSendAndReceive(QUEUE_NAME, (Object) new String(message), new MessagePostProcessor() {
				@Override
				public Message postProcessMessage(Message message) throws AmqpException {
					return message;
				}
			});
//			mqTemplate.convertAndSend(EXCHANGE_NAME,routingKey,message);
			System.out.println("[x] rout:["+routingKey+"] sent:" + message);
		}


//		mqTemplate.setExchange(EXCHANGE_NAME);
//		mqTemplate.setRoutingKey("1.v1");
//		mqTemplate.convertAndSend("hello");

//		mqTemplate.convertAndSend(EXCHANGE_NAME,"1.v2","hello2");
//
////		mqTemplate.convertAndSend("non-existent_exchange","1.v2","hello2");
//		System.out.println("end");






	}
}
