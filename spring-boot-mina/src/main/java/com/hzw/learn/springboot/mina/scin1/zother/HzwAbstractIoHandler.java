package com.hzw.learn.springboot.mina.scin1.zother;

import org.apache.mina.core.service.IoHandlerAdapter;
import org.apache.mina.core.session.IdleStatus;
import org.apache.mina.core.session.IoSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public abstract class HzwAbstractIoHandler extends IoHandlerAdapter {
	
	private Logger log = LoggerFactory.getLogger("HANDLER");
	
	public abstract String inOrOut();
	
	@Override
	public void sessionCreated(IoSession session) throws Exception {
		log.info("【{}】会话已创建：CsessionId:{} Handler对象的HashCode:{}", inOrOut(), session.getId(), this.hashCode());
	}

	@Override
	public void sessionOpened(IoSession session) throws Exception {
		log.info("【{}】会话已打开：CsessionId:{}", inOrOut(), session.getId());
	}

	@Override
	public void sessionClosed(IoSession session) throws Exception {
		log.info("【{}】会话已关闭：CsessionId:{}", inOrOut(),session.getId());
	}

	@Override
	public void sessionIdle(IoSession session, IdleStatus status) throws Exception {
		log.info("【" +inOrOut()+"】会话闲置：CsessionId:{} IdleStatus:{} idlecount:{} 时间：{}", session.getId(), status.toString(), session.getBothIdleCount(), System.currentTimeMillis()/1000);
	}

	@Override
	public void exceptionCaught(IoSession session, Throwable cause) throws Exception {
		log.info("【" +inOrOut()+"】会话异常：CsessionId:{} Erromessage：{}", session.getId(), cause.getMessage());
		cause.printStackTrace();
	}

	@Override
	public void messageReceived(IoSession session, Object message) throws Exception {
		log.info("【" +inOrOut()+"】会话接收到消息：CsessionId:{} 消息:{}", session.getId(), message);
//		session.setAttribute("message", message);
	}

	@Override
	public void messageSent(IoSession session, Object message) throws Exception {
		log.info("【" +inOrOut()+"】会话发送消息：CsessionId:{} 消息:{}", session.getId(), message);
	}

	@Override
	public void inputClosed(IoSession session) throws Exception {
		log.info("【" +inOrOut()+"】半双工TCP通道已关闭：CsessionId:{}", session.getId());
	}
}
