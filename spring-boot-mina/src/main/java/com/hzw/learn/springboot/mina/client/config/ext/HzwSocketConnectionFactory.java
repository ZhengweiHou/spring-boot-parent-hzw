package com.hzw.learn.springboot.mina.client.config.ext;

import org.apache.commons.pool.BasePoolableObjectFactory;
import org.apache.mina.core.future.ConnectFuture;
import org.apache.mina.core.session.IoSession;
import org.apache.mina.transport.socket.nio.NioSocketConnector;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class HzwSocketConnectionFactory extends BasePoolableObjectFactory<IoSession> {
	Logger log = LoggerFactory.getLogger("POOL");

	private NioSocketConnector connector;

	public HzwSocketConnectionFactory(NioSocketConnector connector) {
		this.connector = connector;
	}
	
	@Override
	public IoSession makeObject() throws Exception {
		ConnectFuture connect = connector.connect();
		connect = connect.awaitUninterruptibly();
		IoSession session = connect.getSession();
		log.info("创建，sessionId:{}", session.getId());
		return session;
	}

	@Override
	public void destroyObject(IoSession session) throws Exception {
		if(session != null) {
			session.closeNow();
		}

		log.info("销毁，sessionId:{}", session == null ? null : session.getId());
	}

	@Override
	public boolean validateObject(IoSession session) {
		log.info("验证，sessionId:{}", session.getId());
		return session.isConnected();
	}

	@Override
	public void activateObject(IoSession session) throws Exception {
		log.info("获取，sessionId:{}", session.getId());
	}

	@Override
	public void passivateObject(IoSession session) throws Exception {
		log.info("passivateObject");
	}
	
	
	


}
