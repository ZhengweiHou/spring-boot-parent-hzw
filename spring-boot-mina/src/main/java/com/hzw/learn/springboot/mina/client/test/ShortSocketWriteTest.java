package com.hzw.learn.springboot.mina.client.test;

import java.util.Scanner;

import org.apache.mina.core.future.ConnectFuture;
import org.apache.mina.core.session.IoSession;
import org.apache.mina.transport.socket.nio.NioSocketConnector;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.hzw.learn.springboot.mina.client.ClientApplication;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = {ClientApplication.class})
public class ShortSocketWriteTest {
	Logger log = LoggerFactory.getLogger("TEST");
	
	@Autowired
	NioSocketConnector socketConnector;
	
	@Test
	public void shortWriteTest() throws NumberFormatException, InterruptedException {
		int n=0;

		Scanner scan = new Scanner(System.in);
		while(true) {
			String str = scan.nextLine();
//			int str = scan.nextInt();
			System.out.println(">>短连接测试>>>>>>>>>" + str);
			if("exit".equals(str)) {
				break;
			}
			
			ConnectFuture connect = socketConnector.connect();
			connect.awaitUninterruptibly();
			IoSession session = connect.getSession();
			session.write("Hello Hzw, 这是短连接客户端发送的消息，请求时间：" + System.currentTimeMillis() + "!");
			session.getCloseFuture().awaitUninterruptibly();
//			session.closeNow();
		}

//		Thread.sleep(new Long("300000"));
		
		System.out.println("结束");
	}
	
}
