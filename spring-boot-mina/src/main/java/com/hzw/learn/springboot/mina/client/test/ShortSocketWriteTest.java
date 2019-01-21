package com.hzw.learn.springboot.mina.client.test;

import java.util.Scanner;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.hzw.learn.springboot.mina.client.ClientApplication;
import com.hzw.learn.springboot.mina.client.config.HzwSocketClientShort;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = { ClientApplication.class })
public class ShortSocketWriteTest {
	Logger log = LoggerFactory.getLogger("TEST");

//	@Autowired
//	NioSocketConnector socketConnector;
	@Autowired
	HzwSocketClientShort client;

	@Test
	public void shortWriteTest() throws NumberFormatException, InterruptedException {
		int n = 0;

		Scanner scan = new Scanner(System.in);
		while (true) {
			String str = scan.nextLine();
			System.out.println(">>短连接测试>>>>>>>>>" + str);

//			ConnectFuture connect = socketConnector.connect();
//			connect.awaitUninterruptibly();
//			IoSession session = connect.getSession();
//			session.write("Hello Hzw, 这是短连接客户端发送的消息，请求时间：" + System.currentTimeMillis() + "!");
//			session.getCloseFuture().awaitUninterruptibly();
//			System.out.println("关闭连接");
			new Thread(() -> {
				String result = null;
				try {
					result = client.write("Hello Hzw, 这是短连接客户端发送的消息，请求时间：" + System.currentTimeMillis() + "!");
				} catch (Exception e) {
					e.printStackTrace();
				}
				log.info("服务端返回：{}", result);
			}).start();

		}
	}

	}
