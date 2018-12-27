package com.hzw.learn.springboot.mina.client.config;

import org.apache.commons.pool.impl.GenericObjectPool;
import org.apache.mina.core.future.WriteFuture;
import org.apache.mina.core.session.IoSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class HzwSocketClient {
	
	Logger log = LoggerFactory.getLogger("CLIENT");
	
	private GenericObjectPool<IoSession>  pool;
	
	public String write(String message) {
		IoSession session = null;
		try {
			session = pool.borrowObject();
		} catch (Exception e) {
			log.error("连接池获取session异常",e);
			return null;
		}
		log.info("获取的sessionId:{}",session.getId());
		
		WriteFuture f = session.write(message + "--客户端SessionId:" + session.getId());
		
		// Handler和当前线程不是同一个线程，这里要等Handler处理完才能继续，可以使用synchronized处理
		try {
			Thread.sleep(50l);
		} catch (InterruptedException e1) {
			;
		}
		
		Throwable t = f.getException();
		if(t != null) {
			t.printStackTrace();
			try {
				pool.invalidateObject(session);
			} catch (Exception e) {
				log.error("连接池废除session【{}】异常",session.getId(),e);
			}
		}
		
		String result = (String) session.getAttribute("message");
		
		try {
			Thread.sleep(1000l);
			pool.returnObject(session);
		} catch (Exception e) {
			log.error("连接池回收session【{}】异常",session.getId(),e);
			return null;
		}
		return result;
	}
	
	public GenericObjectPool<IoSession> getPool(){
		return pool;
	}
	
	public void setPool(GenericObjectPool<IoSession> pool) {
		this.pool = pool;
	}
	
	
}
