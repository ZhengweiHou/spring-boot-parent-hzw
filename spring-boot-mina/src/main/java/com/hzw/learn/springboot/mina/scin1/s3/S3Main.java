package com.hzw.learn.springboot.mina.scin1.s3;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class S3Main {

	private static Logger log = LoggerFactory.getLogger("S3");

	public static void main(String[] args) {
		log.info("启动S3...");
		new S3();
	}
}
