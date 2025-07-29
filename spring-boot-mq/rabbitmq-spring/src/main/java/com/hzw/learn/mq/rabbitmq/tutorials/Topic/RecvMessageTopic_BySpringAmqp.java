package com.hzw.learn.mq.rabbitmq.tutorials.Topic;

import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
import org.springframework.amqp.rabbit.listener.adapter.MessageListenerAdapter;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

public class RecvMessageTopic_BySpringAmqp {
    public static void main(String[] args) throws IOException, TimeoutException {
        String EXCHANGE_NAME = "hzw.Topic_exchange2";

        CachingConnectionFactory connectionFactory = new CachingConnectionFactory();
//		connectionFactory.setAddresses("localhost:5672");
        connectionFactory.setAddresses("localhost");
        connectionFactory.setUsername("admin");
        connectionFactory.setPassword("admin");
        connectionFactory.setVirtualHost("test");


        AmqpAdmin amqpAdmin = new RabbitAdmin(connectionFactory);

//        Queue queue = new AnonymousQueue();
        Queue queue = new Queue("spring_amqp_hzw_queue");
//        TopicExchange topicExchange = new TopicExchange(EXCHANGE_NAME, true, true);
        TopicExchange topicExchange = new TopicExchange(EXCHANGE_NAME, true, false);
        Binding binding = BindingBuilder.bind(queue).to(topicExchange)
//				.with("1.*");
                .with("*");

        amqpAdmin.declareQueue(queue);
        amqpAdmin.declareExchange(topicExchange);
        amqpAdmin.declareBinding(binding);

        SimpleMessageListenerContainer messageListnerCon = new SimpleMessageListenerContainer();
        messageListnerCon.setConnectionFactory(connectionFactory);
//        messageListnerCon.setAcknowledgeMode(AcknowledgeMode.NONE);

        MessageListenerAdapter mla = new MessageListenerAdapter();
        mla.setDelegate(new MessageListener() {
            public void onMessage(Message message) {
                String replyto = message.getMessageProperties().getReplyTo();
                System.out.println("replyTo:" + replyto);
//				System.out.println(new Gson().toJson(message));
                String msgstr = new String(message.getBody());
                System.out.println(
                        messageListnerCon.getActiveConsumerCount()
                                + message.getMessageProperties().getConsumerTag()
                                + message.getMessageProperties().getDeliveryTag()
                                + ":"
                                + msgstr);
                try {
                    for (int i = 0; i < 60; i++) {
                        Thread.sleep(1000);
                        System.out.println("wait" + msgstr + " " + i);
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        messageListnerCon.setMessageListener(mla);
        messageListnerCon.setAutoDeclare(true);
        messageListnerCon.setAutoStartup(true);
        messageListnerCon.setQueues(queue);
        messageListnerCon.setConcurrency("1-100"); // 设置concurrency数量，min-max，channel数量可弹性调整
        messageListnerCon.setPrefetchCount(1);
        messageListnerCon.setConsecutiveIdleTrigger(1);
        messageListnerCon.setConsecutiveActiveTrigger(1);   // 判断container为激活状态的触发数量
        messageListnerCon.setStartConsumerMinInterval(100); // 动态channel数量调整的时间间隔，单位毫秒


        // 注册一个退出钩子
        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            System.out.println("Shutdown hook called, doing cleanup...");
            // 在这里添加您需要在JVM关闭时执行的代码
            // 例如，关闭数据库连接，保存状态等
            messageListnerCon.shutdown();
            for (int i = 0; i < 5; i++) {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                System.out.println("main wait" + i);
            }

        }));
        messageListnerCon.start();




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
}
