package com.hzw.learn.springboot.mina.scin1.zother;

import org.apache.mina.core.session.IoSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public abstract class HzwLongTermClientIoHandler extends HzwAbstractIoHandler {

	Logger log = LoggerFactory.getLogger(this.getClass());
 
	@Override
	public void exceptionCaught(IoSession session, Throwable cause) throws Exception {
		super.exceptionCaught(session, cause);
		// 唤醒等待的线程
		synchronized (session) {
			session.notifyAll();
		}
	}

	@Override
	public void messageReceived(IoSession session, Object message) throws Exception {
		super.messageReceived(session, message);
		// 将响应放在session的属性中
		session.setAttribute("MESSAGE", message);
		// 唤醒等待的线程
		synchronized (session) {
			session.notifyAll();
		}
	}
	
}
