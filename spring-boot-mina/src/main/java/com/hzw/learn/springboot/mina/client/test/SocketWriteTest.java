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
import com.hzw.learn.springboot.mina.client.config.HzwSocketClient;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = {ClientApplication.class})
public class SocketWriteTest {
	Logger log = LoggerFactory.getLogger("【TEST】");
	
	@Autowired
	HzwSocketClient client;
	
	@Test
	public void writeTest() throws NumberFormatException, InterruptedException {
		int n=0;

		Scanner scan = new Scanner(System.in);
		while(true) {
			String str = scan.nextLine();
			System.out.println(">>>>>>>>>>>" + str);
			if("exit".equals(str)) {
				break;
			}
			
			new Thread(() -> {
				String result = client.write("Hello Hzw, 这是客户端发送的消息，请求时间：" + System.currentTimeMillis() + "!");
				log.info("服务端返回：{}", result);
			}).start();
		}

//		Thread.sleep(new Long("300000"));
		
		System.out.println("结束");
	}
	
}
