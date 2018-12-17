package com.hzw.learn.springboot.mina.client.config;

import org.apache.commons.pool.impl.GenericObjectPool;
import org.apache.mina.core.session.IoSession;

public class HzwSocketClient {
	private GenericObjectPool<IoSession>  pool;
	
	public String write(String message) {
		IoSession session = null;
		try {
			session = pool.borrowObject();
		} catch (Exception e) {
			System.out.println("连接池异常");
			e.printStackTrace();
			return null;
		}
		
		session.write(message);
		
		String result = (String) session.getAttribute("message");
		return result;
	}
	
	public GenericObjectPool<IoSession> getPool(){
		return pool;
	}
	
	public void setPool(GenericObjectPool<IoSession> pool) {
		this.pool = pool;
	}
	
	
}
