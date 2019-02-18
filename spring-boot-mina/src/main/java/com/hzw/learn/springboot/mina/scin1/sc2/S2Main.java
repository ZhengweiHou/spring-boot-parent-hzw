package com.hzw.learn.springboot.mina.scin1.sc2;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.hzw.learn.springboot.mina.scin1.sc2.s2.S2;

public class S2Main {
	static Logger log = LoggerFactory.getLogger("S2");

	public static void main(String[] args) {
		log.info("启动S2...");
		new S2();
	}
}
