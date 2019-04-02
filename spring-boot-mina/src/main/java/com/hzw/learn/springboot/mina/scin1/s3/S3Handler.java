package com.hzw.learn.springboot.mina.scin1.s3;

import org.apache.mina.core.session.IdleStatus;
import org.apache.mina.core.session.IoSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.hzw.learn.springboot.mina.scin1.zother.HzwAbstractIoHandler;

public class S3Handler extends HzwAbstractIoHandler {
	Logger log = LoggerFactory.getLogger("S3");
	@Override
	public void messageReceived(IoSession session, Object message) throws Exception {
//		log.info("会话接收到消息：SsessionId:" + session.getId() + " 消息:" + message);
		super.messageReceived(session, message);
		String mstr = (String)message;
		Long sLong = Long.parseLong(mstr.split("==")[0]);
//		Thread.sleep(sLong);
		session.write("Hello boy, 服务端已接收到信息!");
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
	
	@Override
	public String inOrOut() {
		return "server";
	}
}
