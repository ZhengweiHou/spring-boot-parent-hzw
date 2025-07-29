package com.hzw.learn.config;

import org.springframework.amqp.core.AmqpAdmin;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
import org.springframework.boot.autoconfigure.amqp.RabbitTemplateConfigurer;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


import com.rabbitmq.client.Channel;


@Configuration
public class RabbitMQConfig {

    public static final String QUEUE_NAME = "hzw_queue";

    @Bean
    public Queue hzwQueue() {
        return new Queue(QUEUE_NAME);
    }

    // @Bean
    // @ConditionalOnMissingBean(name = "replyQueue")
    // public Queue replyQueue(AmqpAdmin rabbitAdmin) {
    // // public Queue replyQueue() {
    //     // return new Queue("xxxxxxxxxxxxx", false, false, false);
    //     // return new Queue("hzw_reply_queue");
    //     return rabbitAdmin.declareQueue();
	// 		// DeclareOk declareOk = this.rabbitTemplate.execute(Channel::queueDeclare);
	// 		// return new Queue(declareOk.getQueue(), false, true, true); // NOSONAR never null
    // }

    @Bean
    @ConditionalOnMissingBean(name = "replyQueue")
    public Queue replyQueue(ConnectionFactory connectionFactory){

        // 手动声明非排他的匿名队列
        RabbitTemplate t = new RabbitTemplate(connectionFactory);
        com.rabbitmq.client.AMQP.Queue.DeclareOk declareOk 
        // = t.execute(Channel::queueDeclare);
        = t.execute(channel -> {
            // return channel.queueDeclare("", true, false, false, null);
            return channel.queueDeclare("", false, false, true, null);
        });
		return new Queue(declareOk.getQueue(), false, false, true); // NOSONAR never null
    }

    @Bean
	// @ConditionalOnSingleCandidate(ConnectionFactory.class)
	public RabbitTemplate rabbitTemplate(RabbitTemplateConfigurer configurer, ConnectionFactory connectionFactory, Queue replyQueue) {
		RabbitTemplate template = new RabbitTemplate();
		configurer.configure(template, connectionFactory);

        template.setCorrelationKey("hzw_reply_correlation");
        template.setReplyAddress(replyQueue.getName());
		return template;
	}

    // @Bean
    // @Primary
    // public RabbitTemplate configuredRabbitTemplate(Queue replyQueue) {
    //     rabbitTemplate.setCorrelationKey("hzw_reply_correlation");
    //     rabbitTemplate.setReplyAddress(replyQueue.getName());
    //     return rabbitTemplate;
    // }

    @Bean
    public SimpleMessageListenerContainer replyListenerContainer(ConnectionFactory connectionFactory, 
                                                                    Queue replyQueue,
                                                                    RabbitTemplate rabbitTemplate) {
        SimpleMessageListenerContainer container = new SimpleMessageListenerContainer(connectionFactory);
        container.setQueueNames(replyQueue.getName());
        container.setMessageListener(rabbitTemplate);
        container.start();
        return container;
    } 
   
}
