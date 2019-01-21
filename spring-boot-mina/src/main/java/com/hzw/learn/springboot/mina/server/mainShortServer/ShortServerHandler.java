package com.hzw.learn.springboot.mina.server.mainShortServer;

import org.apache.mina.core.service.IoHandlerAdapter;
import org.apache.mina.core.session.IdleStatus;
import org.apache.mina.core.session.IoSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ShortServerHandler extends IoHandlerAdapter {
	
	Logger log = LoggerFactory.getLogger("ShortServerHandler");
	
	private static ShortServerHandler samplMinaServerHandler = null;     
	    
	    public static ShortServerHandler getInstances() {     
	        if (null == samplMinaServerHandler) {     
	            samplMinaServerHandler = new ShortServerHandler();     
	        }     
	        return samplMinaServerHandler;     
	    }     
	    
		private ShortServerHandler() {     
	    
	    }
	 
		@Override
		public void exceptionCaught(IoSession session, Throwable cause) throws Exception {
			log.info("会话异常：SsessionId:" + session.getId() + " Erromessage:" + cause.getMessage());
		}
	 
		@Override
		public void messageReceived(IoSession session, Object message) throws Exception {
			log.info("会话接收到消息：SsessionId:" + session.getId() + " 消息:" + message);
			session.write("Hello boy, 服务端已接收到信息!");
		}
	 
		@Override
		public void messageSent(IoSession session, Object message) throws Exception {
			log.info("会话发送消息：SsessionId:" + session.getId() + " 消息:" + message);
		}
	 
		@Override
		public void sessionClosed(IoSession session) throws Exception {
			log.info("会话已关闭：SsessionId:" + session.getId());
		}
	 
		@Override
		public void sessionCreated(IoSession session) throws Exception {
			log.info("会话已创建：SsessionId:" + session.getId());
		}
	 
		@Override
		public void sessionIdle(IoSession session, IdleStatus status) throws Exception {
			log.info("会话闲置：SsessionId:" + session.getId() + " IdleStatus:" + status.toString());
			session.closeNow();
		}
	 
		@Override
		public void sessionOpened(final IoSession session) throws Exception {
			log.info("会话已打开：SsessionId:" + session.getId());
			session.write("Hello boy, 服务端会话已打开!");
		}     

		@Override
		public void inputClosed(IoSession session) throws Exception {
			log.info("半双工TCP通道已关闭：SsessionId:" + session.getId());
			Thread.sleep(new Long("3000"));
			session.closeNow();
			
		}




		
}
