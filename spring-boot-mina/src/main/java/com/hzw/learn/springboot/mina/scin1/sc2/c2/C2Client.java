package com.hzw.learn.springboot.mina.scin1.sc2.c2;

import java.util.concurrent.locks.ReentrantLock;

import org.apache.mina.core.future.CloseFuture;
import org.apache.mina.core.future.ConnectFuture;
import org.apache.mina.core.future.WriteFuture;
import org.apache.mina.core.session.IoSession;
import org.apache.mina.transport.socket.nio.NioSocketConnector;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class C2Client {
	Logger log = LoggerFactory.getLogger("C2");

	public Object write(Object message) throws Exception {
		NioSocketConnector connector = C2.getConnector();

		ConnectFuture connectFuture = connector.connect();
		connectFuture.awaitUninterruptibly();
		
//		connector.connect();

//		if (connectFuture.isDone()) {
//			if (!connectFuture.isConnected()) { // 若在指定时间内没连接成功，则抛出异常
//				log.info("fail to connect " + connector.getDefaultRemoteAddress());
//				connector.dispose(); // 不关闭的话会运行一段时间后抛出，too many open files异常，导致无法连接
//
//				throw new Exception();
//			}
//		}

		IoSession session = connectFuture.getSession();

		log.info("SessionId[{}]发送内容[{}]", session.getId(), message);

		WriteFuture f = session.write(message);

//		if (!session.isClosing()) {
//			try {
//				Thread.sleep(10l);
//			} catch (InterruptedException e) {
//			}
//		}
//
		session.getCloseFuture().awaitUninterruptibly();
		Object result = session.getAttribute("MESSAGE");
		log.info("服务端返回：{}", result);
		return result;
	}

	public Object writeSyn(Object message) {
		writeThread writeThread = new writeThread(message);
		writeThread.start();

		try {
			Thread.sleep(1l);
//			writeThread.lock.lock();
			while(!writeThread.lock.isLocked()) {
				Thread.sleep(1l);
			}
			writeThread.lock.lock();

			log.info("333333333333333333");
//			return writeThread.result;
		} catch (InterruptedException e) {
			e.printStackTrace();
		} finally {
			if (writeThread.lock.isHeldByCurrentThread())
				writeThread.lock.unlock();
		}
		log.info("4444444444444444444444");
		return writeThread.result;
	}

	class writeThread extends Thread {

		public ReentrantLock lock = null;
		public volatile Object result = null;
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
