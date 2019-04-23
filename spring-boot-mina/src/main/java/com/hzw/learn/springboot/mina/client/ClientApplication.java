package com.hzw.learn.springboot.mina.client;

import java.util.Scanner;

import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.ComponentScan;

import com.hzw.learn.springboot.mina.client.config.HzwSocketClientLong;

@SpringBootApplication
@ComponentScan(value= {"com.hzw.learn.springboot.mina.client"})
public class ClientApplication {
	
	
	public static void main(String[] args) {
		ConfigurableApplicationContext appContext = SpringApplication.run(ClientApplication.class, args);
		
		HzwSocketClientLong client = appContext.getBean(HzwSocketClientLong.class);

		
		int n=0;

		Scanner scan = new Scanner(System.in);
		int count = 0;
		System.out.println("客户端启动成功");
		while(true) {
			System.out.println("输入发送内容：");
			String str = scan.nextLine();
			if(str.isEmpty()) {
				str = "600";
			}
			String message = str+"==第" + ++count + "条消息";
			
			new Thread(() -> {
				String result = null;
				try {
					result = client.write(message);
					System.out.println("================" + result);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}).start();
		}
	}
}
