package com.hzw.learn.springboot.mina.scin1.sc2.c2;

import org.apache.mina.core.future.ConnectFuture;
import org.apache.mina.core.future.WriteFuture;
import org.apache.mina.core.session.IoSession;
import org.apache.mina.transport.socket.nio.NioSocketConnector;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class C2Client {
	Logger log = LoggerFactory.getLogger("C2");
	public String write(Object message) {
		NioSocketConnector connector = C2.getConnector();

		ConnectFuture connect = connector.connect();
		connect = connect.awaitUninterruptibly();
		IoSession session = connect.getSession();

		log.info("SessionId[{}]发送内容[{}]", session.getId(), message);
		
		WriteFuture f = session
				.write(message);
		session.getCloseFuture().awaitUninterruptibly();

		String result = (String) session.getAttribute("MESSAGE");
		log.info("服务端返回：{}", result);
		return result;
	}
	
	public String writeSyn(Object message) {
		new Thread(() -> {
			NioSocketConnector connector = C2.getConnector();

			ConnectFuture connect = connector.connect();
			connect = connect.awaitUninterruptibly();
			IoSession session = connect.getSession();

			log.info("SessionId[{}]发送内容[{}]", session.getId(), message);
			
			WriteFuture f = session
					.write(message);
			session.getCloseFuture().awaitUninterruptibly();

			String result = (String) session.getAttribute("MESSAGE");
			log.info("服务端返回：{}", result);
		}).start();
		return "S2完成socket异步转发";
	}
	
}
