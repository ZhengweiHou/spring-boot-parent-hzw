package com.hzw.learn.springboot.mina.client.config.ext;

import java.net.InetSocketAddress;

import org.apache.commons.pool.BasePoolableObjectFactory;
import org.apache.mina.core.future.ConnectFuture;
import org.apache.mina.core.session.IoSession;
import org.apache.mina.transport.socket.nio.NioSocketConnector;

public class HzwSocketConnectionFactory extends BasePoolableObjectFactory<IoSession> {

	private NioSocketConnector connector;

	public HzwSocketConnectionFactory(NioSocketConnector connector) {
		this.connector = connector;
	}
	
	@Override
	public IoSession makeObject() throws Exception {
		
		
//		ConnectFuture connect = connector.connect(new InetSocketAddress("localhost", 8081));
		ConnectFuture connect = connector.connect();
//		System.out.println("111");
		connect = connect.awaitUninterruptibly();
//		System.out.println("222");
		IoSession session = connect.getSession();
//		System.out.println("333");
		return session;
		
	}


}
