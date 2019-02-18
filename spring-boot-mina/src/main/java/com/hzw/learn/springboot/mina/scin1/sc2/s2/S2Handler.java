package com.hzw.learn.springboot.mina.scin1.sc2.s2;

import org.apache.mina.core.session.IdleStatus;
import org.apache.mina.core.session.IoSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.hzw.learn.springboot.mina.scin1.sc2.c2.C2Client;
import com.hzw.learn.springboot.mina.scin1.zother.HzwAbstractIoHandler;

public class S2Handler extends HzwAbstractIoHandler {
	Logger log = LoggerFactory.getLogger("S2");
	@Override
	public void messageReceived(IoSession session, Object message) throws Exception {
		super.messageReceived(session, message);
//		Object result = new C2Client().write(message);
		Object result = new C2Client().writeSyn(message);
		session.write(result);
		session.close(false);
	}
	@Override
	public void exceptionCaught(IoSession session, Throwable cause)
			throws Exception {
		super.exceptionCaught(session, cause);
		// 关闭连接
		session.close(false);
	}

	@Override
	public void sessionIdle(IoSession session, IdleStatus status)
			throws Exception {
		super.sessionIdle(session, status);
		// 超过一定时间没有响应的时候关闭连接
		log.warn("对方没有响应，等待超时，关闭连接");
		session.close(false);
	}
}
