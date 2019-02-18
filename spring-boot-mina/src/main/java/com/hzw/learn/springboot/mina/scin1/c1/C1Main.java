package com.hzw.learn.springboot.mina.scin1.c1;

import java.util.Scanner;

import org.apache.mina.core.future.ConnectFuture;
import org.apache.mina.core.future.WriteFuture;
import org.apache.mina.core.session.IoSession;
import org.apache.mina.transport.socket.nio.NioSocketConnector;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class C1Main {

	static Logger log = LoggerFactory.getLogger("C1");

	public static void main(String[] args) {
		log.info("启动C1...");
		Scanner scan = new Scanner(System.in);
		while (true) {
//			String str = scan.nextLine();
			long sLong = scan.nextLong();
			System.out.println(">>C1>>>>>>>>>" + sLong);
			
			new Thread(() -> {
				NioSocketConnector connector = C1.getConnector();

				ConnectFuture connect = connector.connect();
				connect = connect.awaitUninterruptibly();
				IoSession session = connect.getSession();

				String message = sLong + "==C1发送的消息" + System.currentTimeMillis();
				
				log.info("SessionId[{}]发送内容[{}]", session.getId(), message);
				
				WriteFuture f = session
						.write(message);
				session.getCloseFuture().awaitUninterruptibly();

				String result = (String) session.getAttribute("MESSAGE");
				log.info("服务端返回：{}", result);
			}).start();

		}
	}
}
