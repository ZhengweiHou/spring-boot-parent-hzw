package com.hzw.learn.springboot.mina.scin1.c1;

import java.net.InetSocketAddress;

import org.apache.mina.core.filterchain.DefaultIoFilterChainBuilder;
import org.apache.mina.filter.codec.ProtocolCodecFilter;
import org.apache.mina.filter.codec.serialization.ObjectSerializationCodecFactory;
import org.apache.mina.transport.socket.nio.NioSocketConnector;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class C1 {
	Logger log = LoggerFactory.getLogger("C1");
	
	private int bindPort = 8502;
	private long timeout = 3000;
	private int idleTime = 2000;
	
	private static NioSocketConnector connector = null;
	
	private C1() {
		connector = new NioSocketConnector();
		
		DefaultIoFilterChainBuilder chain = connector.getFilterChain();
		chain.addLast("C1chain", new ProtocolCodecFilter(new ObjectSerializationCodecFactory()));
		InetSocketAddress socketAddress = new InetSocketAddress(bindPort);
		log.info("连接：" + socketAddress.getHostName() + ":" + socketAddress.getPort());
		connector.setDefaultRemoteAddress(socketAddress);
		connector.setHandler(new C1Handler());
		connector.setConnectTimeoutMillis(timeout);
		connector.getSessionConfig().setBothIdleTime(idleTime);
	}
	
	public static NioSocketConnector getConnector() {
		if(connector == null)
			new C1();
		return connector;
	}
	
}
