package com.hzw.learn.springboot.mina.client.test;

import java.util.Date;
import java.util.Scanner;

import com.hzw.learn.springboot.mina.client.ShortClientApplication;
import org.apache.mina.core.future.ConnectFuture;
import org.apache.mina.core.future.WriteFuture;
import org.apache.mina.core.session.IoSession;
import org.apache.mina.transport.socket.nio.NioSocketConnector;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.hzw.learn.springboot.mina.client.config.HzwSocketClientLong;
import com.hzw.learn.springboot.mina.client.config.HzwSocketClientShort;
import com.hzw.learn.springboot.mina.client.config.ext.HzwSocketConnectFactory;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = {ShortClientApplication.class})
public class SocketWriteTest {
	Logger log = LoggerFactory.getLogger("TEST");
	
	@Autowired
	HzwSocketClientLong client;
	
	@Autowired
	HzwSocketConnectFactory connectFactor;
	
	@Test
	public void longWriteTest() throws NumberFormatException, InterruptedException {

		Scanner scan = new Scanner(System.in);
		int count = 0;
		System.out.println("长连接客户端启动成功");
		while(true) {
			System.out.println("》输入发送内容：");
//			String str = scan.nextLine();
			String str = new Date().toString();

			if(str.isEmpty()) str = "1";
			String message = str+"==第" + ++count + "条消息";
			
			new Thread(() -> {
				String result = null;
				try {
					result = client.write(message);
				} catch (Exception e) {
					e.printStackTrace();
				}
				log.info("=========：{}", result);
			}).start();

			Thread.sleep(15000);
		}

	}
	
	@Test
	public void shortWriteTest() throws NumberFormatException, InterruptedException {

		Scanner scan = new Scanner(System.in);
		int count = 0;
		System.out.println("SHORT客户端启动成功");
		while(true) {
			System.out.println("输入发送内容：");
			//			String str = scan.nextLine();
			String str = new Date().toString();

			if(str.isEmpty()) str = "1";
			String message = str+"==第" + ++count + "条消息";
//			new Thread(() -> {
			HzwSocketClientShort client = null;
			
				try {
					client = new HzwSocketClientShort();
					client.setConnector(connectFactor.newShortConnectot());
					String result = client.write(message);
					log.info("==========" + result);
				} catch (Exception e) {
					e.printStackTrace();
				}finally {
					log.info("==========发送结束");
					client.getConnector().dispose();
				}
//			}).start();

			Thread.sleep(3500);
		}

	}

	@Test
	public void test1() throws InterruptedException {
		NioSocketConnector connector = connectFactor.newShortConnectot();
		IoSession session = null;
		ConnectFuture connectFuture = connector.connect();
		connectFuture.awaitUninterruptibly();

		session = connectFuture.getSession();

//		WriteFuture f = session.write("hahaha,你看不见我！");
Thread.sleep(100000);

		System.exit(1);
//		session.closeOnFlush();
	}
	
}
