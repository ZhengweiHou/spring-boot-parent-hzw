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
import com.hzw.learn.springboot.mina.client.config.HzwSocketClientLong;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = {ClientApplication.class})
public class SocketWriteTest {
	Logger log = LoggerFactory.getLogger("TEST");
	
	@Autowired
	HzwSocketClientLong client;
	
	@Test
	public void writeTest() throws NumberFormatException, InterruptedException {
		int n=0;

		Scanner scan = new Scanner(System.in);
		int count = 0;
		System.out.println("客户端启动成功");
		while(true) {
			System.out.println("输入发送内容：");
			String str = scan.nextLine();
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
		}

	}
	
}
