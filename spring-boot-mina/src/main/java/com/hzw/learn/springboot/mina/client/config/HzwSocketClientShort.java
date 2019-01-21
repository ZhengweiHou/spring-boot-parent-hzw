package com.hzw.learn.springboot.mina.client.config;

import org.apache.commons.pool.impl.GenericObjectPool;
import org.apache.mina.core.future.WriteFuture;
import org.apache.mina.core.session.IoSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class HzwSocketClientShort {

	Logger log = LoggerFactory.getLogger("SortCLIENT");

	private GenericObjectPool<IoSession> pool;

	public String write(String message) throws Exception {
		IoSession session = null;
		session = pool.borrowObject();
		log.info("获取的sessionId:{}", session.getId());

		WriteFuture f = session.write(message + "--客户端SessionId:" + session.getId());

		session.getCloseFuture().awaitUninterruptibly();

		// Handler和当前线程不是同一个线程，这里要等Handler处理完才能继续，可以使用synchronized处理
		Thread.sleep(50l);

		Throwable t = f.getException();
		if (t != null) {
			t.printStackTrace();
			// 连接池废弃session
			pool.invalidateObject(session);
		}

		String result = (String) session.getAttribute("message");
		// 连接池废弃session
		pool.invalidateObject(session);
		return result;
	}

	public GenericObjectPool<IoSession> getPool() {
		return pool;
	}

	public void setPool(GenericObjectPool<IoSession> pool) {
		this.pool = pool;
	}

}
