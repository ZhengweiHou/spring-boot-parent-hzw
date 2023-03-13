package com.houzw.learn.rocketmq.hzwtest.produce;

import com.houzw.learn.rocketmq.hzwtest.Constants;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.spring.core.RocketMQTemplate;
import org.apache.rocketmq.spring.support.RocketMQHeaders;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessagingException;
import org.springframework.messaging.support.MessageBuilder;

import javax.annotation.Resource;

@SpringBootApplication(scanBasePackages = "com.houzw.learn.rocketmq.hzwtest.produce")
public class ProduceApplication implements CommandLineRunner {

	@Resource
	private RocketMQTemplate rocketMQTemplate;

//	@Resource
//	private HzwTransactionListener hzwTransactionListener;

	public static void main(String[] args) {
		ConfigurableApplicationContext ctx = SpringApplication.run(ProduceApplication.class, args);
		HzwTransactionListener a = ctx.getBean(HzwTransactionListener.class);
		System.out.println("=");
	}

	@Override
	public void run(String... args) throws Exception {

		// 事务消息测试
		testRocketMQTemplateTransaction();
	}

	// 事务消息测试案例
	private void testRocketMQTemplateTransaction() throws MessagingException {

		String[] tags = new String[] {"TagA", "TagB", "TagC", "TagD", "TagE"};
		for (int i = 0; i < 2; i++) {
			try {
				Message msg = MessageBuilder.withPayload("rocketMQTemplate transactional message " + i).
						setHeader(RocketMQHeaders.TRANSACTION_ID, "KEY_" + i).build();

				SendResult sendResult = rocketMQTemplate.sendMessageInTransaction(
						Constants.TransTopic + ":" + tags[i % tags.length], msg, null);

				System.out.printf("------rocketMQTemplate send Transactional msg body = %s , sendResult=%s %n",
						msg.getPayload(), sendResult.getSendStatus());

				Thread.sleep(10);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
}
