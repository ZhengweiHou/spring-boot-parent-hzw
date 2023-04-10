package com.hzw.learn.springboot.mina.client.config.ext;

import org.apache.commons.pool.impl.GenericObjectPool;
import org.apache.mina.core.service.IoHandlerAdapter;
import org.apache.mina.core.session.IdleStatus;
import org.apache.mina.core.session.IoSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.stereotype.Component;

@Component
public class HzwIoHandler_short extends IoHandlerAdapter {
	
	private Logger log = LoggerFactory.getLogger("HANDLER");
	
	@Autowired
	private ConfigurableApplicationContext applications;
	
	@Override
	public void sessionCreated(IoSession session) throws Exception {
//		log.info("会话已创建：CsessionId:{} Handler对象的HashCode:{}", session.getId(), this.hashCode());
		this.log(session);
	}

	@Override
	public void sessionOpened(IoSession session) throws Exception {
//		log.info("会话已打开：CsessionId:{}",session.getId());
		this.log(session);
	}

	@Override
	public void sessionClosed(IoSession session) throws Exception {
//		log.info("会话已关闭：CsessionId:{}",session.getId());
		this.log(session);
	}

	@Override
	public void sessionIdle(IoSession session, IdleStatus status) throws Exception {
		this.log(session);
		log.info("会话闲置：CsessionId:{} IdleStatus:{} idlecount:{} 时间：{}", session.getId(), status.toString(), session.getBothIdleCount(), System.currentTimeMillis()/1000);
		session.close(false);
	}

	@Override
	public void exceptionCaught(IoSession session, Throwable cause) throws Exception {
		this.log(session);
		log.info("会话异常：CsessionId:{} Erromessage：{}", session.getId(), cause.getMessage());
		cause.printStackTrace();
	}

	@Override
	public void messageReceived(IoSession session, Object message) throws Exception {
		this.log(session);
		log.info("会话接收到消息：CsessionId:{} 消息:{}", session.getId(), message);

		session.setAttribute("message", message);
		synchronized (session) {
			session.notify();
		}
		session.close(false);
	}

	@Override
	public void messageSent(IoSession session, Object message) throws Exception {
		this.log(session);
		log.info("会话发送消息：CsessionId:{} 消息:{}", session.getId(), message);

	}

	@Override
	public void inputClosed(IoSession session) throws Exception {
//		log.info("半双工TCP通道已关闭：CsessionId:{}", session.getId());
		this.log(session);
		System.out.println("hahah");
		// 对方关闭了
//		session.closeNow();
	}
	
	private void log(IoSession session) {
		log.info("{}  =sessionId:{}",
				Thread.currentThread().getStackTrace()[2].getMethodName(),
				session.getId()
				);
	}
	
}
