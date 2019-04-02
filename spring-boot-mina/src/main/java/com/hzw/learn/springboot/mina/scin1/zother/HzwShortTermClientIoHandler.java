package com.hzw.learn.springboot.mina.scin1.zother;

import org.apache.mina.core.session.IdleStatus;
import org.apache.mina.core.session.IoSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class HzwShortTermClientIoHandler extends HzwAbstractIoHandler {
	
	private Logger logger = LoggerFactory.getLogger(getClass());

	@Override
	public void exceptionCaught(IoSession session, Throwable cause)
			throws Exception {
		super.exceptionCaught(session, cause);
		// 关闭连接
		session.close(false);
	}

	@Override
	public void messageReceived(IoSession session, Object message)
			throws Exception {
		super.messageReceived(session, message);
		// 将响应放在session的属性中
		session.setAttribute("MESSAGE", message);
		// 关闭连接
		session.close(false);
	}

	@Override
	public void sessionIdle(IoSession session, IdleStatus status)
			throws Exception {
		// 超过一定时间没有响应的时候关闭连接
		logger.warn("对方没有响应，等待超时，关闭连接");
		session.close(false);
	}
	
	@Override
	public String inOrOut() {
		return "short";
	}
	
}
