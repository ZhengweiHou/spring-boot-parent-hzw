package com.hzw.learn.springboot.mina.server;

import org.apache.mina.core.filterchain.IoFilterAdapter;
import org.apache.mina.core.service.IoHandler;
import org.apache.mina.core.session.IdleStatus;
import org.apache.mina.core.session.IoSession;

public class ServerHandler extends IoFilterAdapter implements IoHandler {
 
private static ServerHandler samplMinaServerHandler = null;     
    
    public static ServerHandler getInstances() {     
        if (null == samplMinaServerHandler) {     
            samplMinaServerHandler = new ServerHandler();     
        }     
        return samplMinaServerHandler;     
    }     
    
	private ServerHandler() {     
    
    }
 
	@Override
	public void exceptionCaught(IoSession session, Throwable cause) throws Exception {
		System.out.println("会话异常：sessionId:" + session.getId() + " Erromessage:" + cause.getMessage());
	}
 
	@Override
	public void messageReceived(IoSession session, Object message) throws Exception {
		System.out.println("会话接收到消息：sessionId:" + session.getId() + " 消息:" + message);
	}
 
	@Override
	public void messageSent(IoSession session, Object message) throws Exception {
		System.out.println("会话发送消息：sessionId:" + session.getId() + " 消息:" + message);
	}
 
	@Override
	public void sessionClosed(IoSession session) throws Exception {
		System.out.println("会话已关闭：sessionId:" + session.getId());
	}
 
	@Override
	public void sessionCreated(IoSession session) throws Exception {
		System.out.println("会话已创建：sessionId:" + session.getId());
	}
 
	@Override
	public void sessionIdle(IoSession session, IdleStatus status) throws Exception {
		System.out.println("会话闲置：sessionId:" + session.getId() + " IdleStatus:" + status.toString());
	}
 
	@Override
	public void sessionOpened(final IoSession session) throws Exception {
		System.out.println("会话已打开：sessionId:" + session.getId());
		session.write("Hello boy, this is server's message!");
	}     

	@Override
	public void inputClosed(IoSession session) throws Exception {
//		System.out.println("Handle the closure of an half-duplex TCP channel");
		System.out.println("半双工TCP通道已关闭：sessionId:" + session.getId());
		Thread.sleep(new Long("3000"));
		session.closeNow();
		
	}




	
}
