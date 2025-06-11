package com.hzw.learn.mq.rabbitmq.tutorials.RPC;

import java.util.Scanner;

import org.springframework.amqp.AmqpException;
import org.springframework.amqp.core.AmqpAdmin;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessagePostProcessor;
import org.springframework.amqp.core.MessageProperties;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.connection.Connection;
import org.springframework.amqp.rabbit.connection.ConnectionListener;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;

import com.rabbitmq.client.ShutdownSignalException;

public class EmitMessageRpc_BySpringAmqp {
	public static void main(String[] args) throws Exception {

		String QUEUE_NAME="hzw.RPC_byspringamqp_queue";
        String REPLY_QUEUE_NAME="hzw.RPC_byspringamqp_reply_queue";

        Queue replyQueue = new Queue(REPLY_QUEUE_NAME);

        // == 创建连接工厂 ==
		CachingConnectionFactory connectionFactory = new CachingConnectionFactory();
//		connectionFactory.setAddresses("localhost:5672");
		connectionFactory.setAddresses("localhost");
		connectionFactory.setUsername("admin");
		connectionFactory.setPassword("admin");
		// connectionFactory.setVirtualHost("test");
		connectionFactory.setVirtualHost("hzw");
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


        // == 创建reply消息队列 ==
        AmqpAdmin amqpAdmin = new RabbitAdmin(connectionFactory);
        String rqueue;
        // rqueue = amqpAdmin.declareQueue(replyQueue); // 使用指定的响应队列
        rqueue = amqpAdmin.declareQueue().getName(); // 使用匿名队列 TODO 必须手动创建匿名队列吗？

        // == 创建Template ==
		RabbitTemplate mqTemplate = new RabbitTemplate(connectionFactory);
        // 设置correlationKey，用于匹配reply消息
        mqTemplate.setCorrelationKey("hzw_reply_correlation");
        mqTemplate.setReplyAddress(rqueue);

        // == 启动监听容器，监听reply消息 ==
        SimpleMessageListenerContainer container = new SimpleMessageListenerContainer(connectionFactory);
        container.setQueueNames(rqueue);
        container.setMessageListener(mqTemplate);
        container.start();

        // == 发送消息 ==
		Scanner scan = new Scanner(System.in);
		int count=0;

		while (true) {
			System.out.println(" [*] input message to send...");
			String message = scan.nextLine();

            message = "[" + ++count + "]==" + message;

			Object responseMsg = mqTemplate.convertSendAndReceive(QUEUE_NAME, (Object) new String(message), new MessagePostProcessor() {
				@Override
				public Message postProcessMessage(Message message) throws AmqpException {

                    MessageProperties mp = message.getMessageProperties();
                    // rabbitmq 数据持久化的位置：
                    ///var/lib/rabbitmq/mnesia/[主机名]/msg_stores/vhosts/[vhost]/queues/[队列]/

                                        

                    // mp.setDeliveryMode(MessageDeliveryMode.NON_PERSISTENT);
                    // mp.setReplyTo(REPLY_QUEUE_NAME);

					return message;
				}
			});

			System.out.println("[x] sented:" + message + " ==> response:" + responseMsg);
		}

	}
}
