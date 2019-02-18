package com.hzw.learn.springboot.mina.scin1.sc2.c2;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.hzw.learn.springboot.mina.scin1.zother.HzwAbstractIoHandler;
import com.hzw.learn.springboot.mina.scin1.zother.HzwLongTermClientIoHandler;
import com.hzw.learn.springboot.mina.scin1.zother.HzwShortTermClientIoHandler;

public class C2Handler 
//extends HzwLongTermClientIoHandler 
extends HzwShortTermClientIoHandler
{
	
	Logger log = LoggerFactory.getLogger("C2");
}
