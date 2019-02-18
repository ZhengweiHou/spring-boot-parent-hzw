package com.hzw.learn.springboot.mina.scin1.c1;

import org.apache.mina.core.session.IoSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.hzw.learn.springboot.mina.scin1.zother.HzwAbstractIoHandler;
import com.hzw.learn.springboot.mina.scin1.zother.HzwLongTermClientIoHandler;
import com.hzw.learn.springboot.mina.scin1.zother.HzwShortTermClientIoHandler;

public class C1Handler 
//extends HzwLongTermClientIoHandler 
extends HzwShortTermClientIoHandler
{
	
	Logger log = LoggerFactory.getLogger("C1");
	
	@Override
	public void inputClosed(IoSession session) throws Exception {
		log.info("半双工TCP通道已关闭：CsessionId:{}", session.getId());
		session.close(false);
	}
}
