package com.hzw.learn.springboot.mina.client.config.ext;

import org.apache.mina.core.service.IoHandlerAdapter;
import org.apache.mina.core.session.IdleStatus;
import org.apache.mina.core.session.IoSession;

public class HzwIoHandler extends IoHandlerAdapter {

	@Override
	public void sessionCreated(IoSession session) throws Exception {
//		super.sessionCreated(session);
		System.out.println("会话已创建：sessionId:" + session.getId());
	}

	@Override
	public void sessionOpened(IoSession session) throws Exception {
//		super.sessionOpened(session);
		System.out.println("会话已打开：sessionId:" + session.getId());
	}

	@Override
	public void sessionClosed(IoSession session) throws Exception {
//		super.sessionClosed(session);
		System.out.println("会话已关闭：sessionId:" + session.getId());
	}

	@Override
	public void sessionIdle(IoSession session, IdleStatus status) throws Exception {
//		super.sessionIdle(session, status);
		System.out.println("会话闲置：sessionId:" + session.getId() + " IdleStatus:" + status.toString());
		session.write("client：闲置会话重连请求");
	}

	@Override
	public void exceptionCaught(IoSession session, Throwable cause) throws Exception {
//		super.exceptionCaught(session, cause);
		System.out.println("会话异常：sessionId:" + session.getId() + " Erromessage:" + cause.getMessage());
	}

	@Override
	public void messageReceived(IoSession session, Object message) throws Exception {
//		super.messageReceived(session, message);
		System.out.println("会话接收到消息：sessionId:" + session.getId() + " 消息:" + message);
	}

	@Override
	public void messageSent(IoSession session, Object message) throws Exception {
//		super.messageSent(session, message);
		System.out.println("会话发送消息：sessionId:" + session.getId() + " 消息:" + message);
	}

	@Override
	public void inputClosed(IoSession session) throws Exception {
//		super.inputClosed(session);
		System.out.println("半双工TCP通道已关闭：sessionId:" + session.getId());
		Thread.sleep(new Long("3000"));
	}
	
}
