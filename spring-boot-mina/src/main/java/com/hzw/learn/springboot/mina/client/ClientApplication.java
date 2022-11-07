package com.hzw.learn.springboot.mina.client;

import java.util.Scanner;

import com.hzw.learn.springboot.mina.client.config.HzwSocketClientLong;
import com.hzw.learn.springboot.mina.client.config.ext.HzwIoHandler_long;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(value = { "com.hzw.learn.springboot.mina.client" })
public class ClientApplication {

	public static void main(String[] args) {
		ConfigurableApplicationContext appContext = SpringApplication.run(ClientApplication.class, args);
		//===================1111111111=========
		HzwSocketClientLong client = appContext.getBean(HzwSocketClientLong.class);
		client.showPoolConfig();
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
			
		//===================2222222222=========
//		HzwSocketConnectFactory connectFactor = appContext.getBean(HzwSocketConnectFactory.class);
//		Scanner scan = new Scanner(System.in);
//		int count = 0;
//		System.out.println("SHORT客户端启动成功");
//		while (true) {
//			System.out.println("输入发送内容：");
//			String str = scan.nextLine();
//			if (str.isEmpty())
//				str = "1";
//			String message = str + "==第" + ++count + "条消息";
//			new Thread(() -> {
//				HzwSocketClientShort client = null;
//
//				try {
//					client = new HzwSocketClientShort();
//					client.setConnector(connectFactor.newShortConnectot());
//					String result = client.write(message);
//					System.out.println("==========" + result);
//				} catch (Exception e) {
//					e.printStackTrace();
//				} finally {
//					System.out.println("==========发送结束");
//					client.getConnector().dispose();
//				}
//			}).start();
//		}
		
		
		
	}
}
