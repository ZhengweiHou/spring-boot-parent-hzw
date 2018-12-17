package com.hzw.learn.springboot.mina.client.test;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.hzw.learn.springboot.mina.client.ClientApplication;
import com.hzw.learn.springboot.mina.client.config.HzwSocketClient;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = {ClientApplication.class})
public class SocketWriteTest {

	@Autowired
	HzwSocketClient client;
	
	@Test
	public void writeTest() throws NumberFormatException, InterruptedException {
		String result = client.write("Hello Hzw, 这是客户端的消息!");
		System.out.println("服务端返回：" + result);
		
		Thread.sleep(new Long("50000"));
		System.out.println("结束");
	}
	
}
