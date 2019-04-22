package com.hzw.learn.springboot.mina.client.config.ext;

import org.apache.commons.pool.BasePoolableObjectFactory;
import org.apache.mina.core.future.ConnectFuture;
import org.apache.mina.core.future.WriteFuture;
import org.apache.mina.core.session.IoSession;
import org.apache.mina.transport.socket.nio.NioSocketConnector;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class HzwSocketConnectionFactory extends BasePoolableObjectFactory<IoSession> {
	Logger log = LoggerFactory.getLogger("POOL");
	
	public static final String NEED_VALIDATE = "NEED_VALIDATE";

	private NioSocketConnector connector;

	public HzwSocketConnectionFactory(NioSocketConnector connector) {
		this.connector = connector;
	}
	
	@Override
	public IoSession makeObject() throws Exception {
		ConnectFuture connect = connector.connect();
		connect = connect.awaitUninterruptibly();
		IoSession session = connect.getSession();
//		log.info("创建，sessionId:{}", session.getId());
		this.log(session);
		return session;
	}

	@Override
	public void destroyObject(IoSession session) throws Exception {
		if(session != null) {
			session.closeNow();
		}
//		log.info("销毁，sessionId:{}", session == null ? null : session.getId());
		this.log(session);
		super.destroyObject(session);
	}

	@Override
	public boolean validateObject(IoSession session) {
//		log.info("验证，sessionId:{}", session.getId());
		this.log(session);
		if(!session.isConnected()) {
			return false;
		}
		if(session.containsAttribute(NEED_VALIDATE)) {
			try {
				WriteFuture writeFuture = session.write("3000==链接检查......");
//				session.get
//				Thread.sleep(50l);
				Throwable t = writeFuture.getException();
				if (t != null) {
					t.printStackTrace();
					return false;
				}
				
			} catch (Exception e) {
				return false;
			}finally {
				session.removeAttribute(NEED_VALIDATE);
			}
		}
		
		return true;
	}

	@Override
	public void activateObject(IoSession session) throws Exception {
//		log.info("获取，sessionId:{}", session.getId());
		this.log(session);
	}

	@Override
	public void passivateObject(IoSession session) throws Exception {
//		log.info("passivateObject");
		this.log(session);
	}
	
	
	private void log(IoSession session) {
		log.info("{}  sessionId:{}",
				Thread.currentThread().getStackTrace()[2].getMethodName(),
				session.getId()
				);
	}


}
