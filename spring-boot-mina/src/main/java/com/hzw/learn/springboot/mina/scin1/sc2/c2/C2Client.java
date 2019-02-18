package com.hzw.learn.springboot.mina.scin1.sc2.c2;

import java.util.concurrent.locks.ReentrantLock;

import org.apache.mina.core.future.ConnectFuture;
import org.apache.mina.core.future.WriteFuture;
import org.apache.mina.core.session.IoSession;
import org.apache.mina.transport.socket.nio.NioSocketConnector;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class C2Client {
	Logger log = LoggerFactory.getLogger("C2");

	public Object write(Object message) {
		NioSocketConnector connector = C2.getConnector();

		ConnectFuture connect = connector.connect();
		connect = connect.awaitUninterruptibly();
		IoSession session = connect.getSession();

		log.info("SessionId[{}]发送内容[{}]", session.getId(), message);

		WriteFuture f = session.write(message);
		session.getCloseFuture().awaitUninterruptibly();

		Object result = session.getAttribute("MESSAGE");
		log.info("服务端返回：{}", result);
		return result;
	}

	public Object writeSyn(Object message) {
		writeThread writeThread = new writeThread(message);
		writeThread.start();
		
		try {
			Thread.sleep(2l);	// 防止主线程在子线程之前加锁
			writeThread.lock.lock();
			log.info("333333333333333333");
//			return writeThread.result;
		} catch (InterruptedException e) {
			e.printStackTrace();
		}finally {
			if(writeThread.lock.isHeldByCurrentThread())
				writeThread.lock.unlock();
		}
		log.info("4444444444444444444444");
		return writeThread.result;
	}

	class writeThread extends Thread {

		public ReentrantLock lock = null;
		public Object result = null;
		public Object message = null;

		public writeThread(Object message) {
			super();
			this.message = message;
			lock = new ReentrantLock();
		}

		@Override
		public void run() {
			log.info("111111111111111111");
			try {
				lock.lock();
				NioSocketConnector connector = C2.getConnector();
				ConnectFuture connect = connector.connect();
				connect = connect.awaitUninterruptibly();
				IoSession session = connect.getSession();
				log.info("SessionId[{}]发送内容[{}]", session.getId(), message);
				WriteFuture f = session.write(message);
				session.getCloseFuture().awaitUninterruptibly();
				result = session.getAttribute("MESSAGE");
				log.info("服务端返回：{}", result);
			} finally {
				if (lock.isHeldByCurrentThread()) {
					lock.unlock();
				}
				log.info("222222222222222222");
			}
		}
	}
}
