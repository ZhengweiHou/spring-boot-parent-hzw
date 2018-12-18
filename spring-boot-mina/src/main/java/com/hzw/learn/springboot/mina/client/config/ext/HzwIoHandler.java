package com.hzw.learn.springboot.mina.client.config.ext;

import org.apache.mina.core.service.IoHandlerAdapter;
import org.apache.mina.core.session.IdleStatus;
import org.apache.mina.core.session.IoSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class HzwIoHandler extends IoHandlerAdapter {
	
	private Logger log = LoggerFactory.getLogger("【HANDLER】");
	
	@Override
	public void sessionCreated(IoSession session) throws Exception {
		log.info("会话已创建：CsessionId:{}",session.getId());
	}

	@Override
	public void sessionOpened(IoSession session) throws Exception {
		log.info("会话已打开：CsessionId:{}",session.getId());
	}

	@Override
	public void sessionClosed(IoSession session) throws Exception {
		log.info("会话已关闭：CsessionId:{}",session.getId());
	}

	@Override
	public void sessionIdle(IoSession session, IdleStatus status) throws Exception {
		log.info("会话闲置：CsessionId:{} IdleStatus:{}", session.getId(), status.toString());
//		session.write("客户端发送的，闲置会话重连请求 CsessionId:" + session.getId());
	}

	@Override
	public void exceptionCaught(IoSession session, Throwable cause) throws Exception {
		log.info("会话异常：CsessionId:{} Erromessage：{}", session.getId(), cause.getMessage());
		cause.printStackTrace();
	}

	@Override
	public void messageReceived(IoSession session, Object message) throws Exception {
		log.info("会话接收到消息：CsessionId:{} 消息:{}", session.getId(), message);
		session.setAttribute("message", message);
	}

	@Override
	public void messageSent(IoSession session, Object message) throws Exception {
		log.info("会话发送消息：CsessionId:{} 消息:{}", session.getId(), message);
	}

	@Override
	public void inputClosed(IoSession session) throws Exception {
		log.info("半双工TCP通道已关闭：CsessionId:{}", session.getId());
		Thread.sleep(new Long("3000"));
	}
	
}
