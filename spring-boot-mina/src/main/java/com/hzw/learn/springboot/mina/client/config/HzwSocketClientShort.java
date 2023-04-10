package com.hzw.learn.springboot.mina.client.config;

import org.apache.mina.core.future.ConnectFuture;
import org.apache.mina.core.future.WriteFuture;
import org.apache.mina.core.session.IoSession;
import org.apache.mina.transport.socket.nio.NioSocketConnector;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class HzwSocketClientShort {

	Logger log = LoggerFactory.getLogger("CLIENT");

	NioSocketConnector connector = null;
	
	public String write(String message) throws Exception {
		IoSession session = null;
		ConnectFuture connectFuture = connector.connect();
		connectFuture.awaitUninterruptibly();
		
		session = connectFuture.getSession();
		
		WriteFuture f = session.write(message + "--客户端SessionId:" + session.getId());
		
		// Handler和当前线程不是同一个线程，这里要等Handler处理完才能继续，可以使用synchronized处理
//		Thread.sleep(50l);
		System.out.println("1111111111111");
		synchronized (session) {
			session.wait();
		}
		System.out.println("222222222222");
		String result = (String) session.getAttribute("message");
		return result;
	}

	public NioSocketConnector getConnector() {
		return connector;
	}

	public void setConnector(NioSocketConnector connector) {
		this.connector = connector;
	}
}
