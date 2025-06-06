package com.hzw.learn.kafkatest.mq.rabbitmq.Tutorials.RPC;

import org.junit.Test;
import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
import org.springframework.amqp.rabbit.listener.adapter.MessageListenerAdapter;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

public class RecvMessageRPC_BySpringAmqp {
    public static void main(String[] args) throws IOException, TimeoutException {
        String EXCHANGE_NAME = "hzw.Topic_exchange";
        String QUEUE_NAME="hzw.RPC_byspringamqp_queue";

        CachingConnectionFactory connectionFactory = new CachingConnectionFactory();
//		connectionFactory.setAddresses("localhost:5672");
        connectionFactory.setAddresses("localhost");
        connectionFactory.setUsername("admin");
        connectionFactory.setPassword("admin");
        connectionFactory.setVirtualHost("test");


        AmqpAdmin amqpAdmin = new RabbitAdmin(connectionFactory);

//        Queue queue = new AnonymousQueue();
        Queue queue = new Queue(QUEUE_NAME);
//        TopicExchange topicExchange = new TopicExchange(EXCHANGE_NAME, true, true);
//        Binding binding = BindingBuilder.bind(queue).to(topicExchange)
////				.with("1.*");
//                .with("*");

        amqpAdmin.declareQueue(queue);
//        amqpAdmin.declareExchange(topicExchange);
//        amqpAdmin.declareBinding(binding);

        MessageListenerAdapter mla = new MessageListenerAdapter();
        mla.setDelegate(new MessageListener() {
            public void onMessage(Message message) {
                String replyto = message.getMessageProperties().getReplyTo();
                System.out.println("replyTo:" + replyto);
//				System.out.println(new Gson().toJson(message));
                System.out.println(
//                        messageListnerCon.getActiveConsumerCount()
                                message.getMessageProperties().getConsumerTag()
                                + message.getMessageProperties().getDeliveryTag()
                                + ":"
                                + new String(message.getBody()));
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        SimpleMessageListenerContainer messageListnerCon = new SimpleMessageListenerContainer();
        messageListnerCon.setConnectionFactory(connectionFactory);
        messageListnerCon.setMessageListener(mla);
        messageListnerCon.setAutoDeclare(true);
        messageListnerCon.setAutoStartup(true);
        messageListnerCon.setQueues(queue);
        messageListnerCon.setConcurrency("10");
//        messageListnerCon.setConcurrency("1-100"); // 设置concurrency数量，min-max，channel数量可弹性调整
//        messageListnerCon.setPrefetchCount(1);
//        messageListnerCon.setConsecutiveIdleTrigger(1);
//        messageListnerCon.setConsecutiveActiveTrigger(1);   // 判断container为激活状态的触发数量
//        messageListnerCon.setStartConsumerMinInterval(100); // 动态channel数量调整的时间间隔，单位毫秒
        messageListnerCon.start();

        SimpleMessageListenerContainer mlc2 = new SimpleMessageListenerContainer();
        mlc2.setConnectionFactory(connectionFactory);
        mlc2.setMessageListener(mla);
        mlc2.setQueues(queue);
        mlc2.setConcurrency("5");
        mlc2.start();
//		while (true){
//			try {
//				Thread.sleep(1000);
//			} catch (InterruptedException e) {
//				e.printStackTrace();
//			}
//		}


//		RabbitTemplate mqTemplate = new RabbitTemplate(connectionFactory);
//		mqTemplate.receive();


//		AmqpAdmin amqpAdmin = new RabbitAdmin(connectionFactory);
//
//		Queue queue = amqpAdmin.declareQueue();
//		SimpleMessageListenerContainer container = new SimpleMessageListenerContainer(connectionFactory);
//		container.setMessageListener();


//		Message msg = new Message();


//		RabbitTemplate mqTemplate = new RabbitTemplate(connectionFactory);
//		mqTemplate.receive(queue.getName());

    }

    @Test
    public void mqbyspringamqp1(){
        String EXCHANGE_NAME = "hzw.Topic_exchange";
        String QUEUE_NAME="hzw.RPC_byspringamqp_queue";

        CachingConnectionFactory connectionFactory = new CachingConnectionFactory();
//		connectionFactory.setAddresses("localhost:5672");
        connectionFactory.setAddresses("localhost");
        connectionFactory.setUsername("admin");
        connectionFactory.setPassword("admin");
        connectionFactory.setVirtualHost("test");


        AmqpAdmin amqpAdmin = new RabbitAdmin(connectionFactory);

//        Queue queue = new AnonymousQueue();
        Queue queue = new Queue(QUEUE_NAME);
//        TopicExchange topicExchange = new TopicExchange(EXCHANGE_NAME, true, true);
//        Binding binding = BindingBuilder.bind(queue).to(topicExchange)
////				.with("1.*");
//                .with("*");

        amqpAdmin.declareQueue(queue);
//        amqpAdmin.declareExchange(topicExchange);
//        amqpAdmin.declareBinding(binding);

        MessageListenerAdapter mla = new MessageListenerAdapter();
        mla.setDelegate(new MessageListener() {
            public void onMessage(Message message) {
                String replyto = message.getMessageProperties().getReplyTo();
                System.out.println("replyTo:" + replyto);
//				System.out.println(new Gson().toJson(message));
                System.out.println(
//                        messageListnerCon.getActiveConsumerCount()
                        message.getMessageProperties().getConsumerTag()
                                + message.getMessageProperties().getDeliveryTag()
                                + ":"
                                + new String(message.getBody()));
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        SimpleMessageListenerContainer mlc1 = new SimpleMessageListenerContainer();
        mlc1.setConnectionFactory(connectionFactory);
        mlc1.setMessageListener(mla);
        mlc1.setQueues(queue);
        mlc1.setConcurrency("10");
        mlc1.start();

        SimpleMessageListenerContainer mlc2 = new SimpleMessageListenerContainer();
        mlc2.setConnectionFactory(connectionFactory);
        mlc2.setMessageListener(mla);
        mlc2.setQueues(queue);
        mlc2.setConcurrency("5");
        mlc2.start();
		while (true){
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
    }

}
