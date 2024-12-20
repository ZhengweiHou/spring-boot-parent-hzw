package com.hzw.learn.kafkatest.mq.rabbitmq.Tutorials.Topic;

import com.rabbitmq.client.ShutdownSignalException;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.connection.Connection;
import org.springframework.amqp.rabbit.connection.ConnectionListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;

import java.util.Scanner;

public class EmitMessageTopic_BySpringAmqp {
	public static void main(String[] args) throws Exception {

		String EXCHANGE_NAME="hzw.Topic_exchange2";

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
		mqTemplate.setReceiveTimeout(30 * 1000);
		mqTemplate.setReplyTimeout(30 * 1000);

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
			Object aa = mqTemplate.convertSendAndReceive(EXCHANGE_NAME, routingKey, message);
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
