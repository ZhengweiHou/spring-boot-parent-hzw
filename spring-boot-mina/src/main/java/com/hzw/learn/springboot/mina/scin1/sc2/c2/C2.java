package com.hzw.learn.springboot.mina.scin1.sc2.c2;

import java.net.InetSocketAddress;

import org.apache.mina.core.filterchain.DefaultIoFilterChainBuilder;
import org.apache.mina.filter.codec.ProtocolCodecFilter;
import org.apache.mina.filter.codec.serialization.ObjectSerializationCodecFactory;
import org.apache.mina.filter.executor.ExecutorFilter;
import org.apache.mina.transport.socket.nio.NioSocketConnector;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class C2 {
	static Logger log = LoggerFactory.getLogger("C2");
	
	private static int bindPort = 8503;
	private static long timeout = 10000;
	private static int idleTime = 2000;
	
	private static NioSocketConnector connector = null;
	
	private C2() {}
	private static  NioSocketConnector initConnector() {
		NioSocketConnector connector = new NioSocketConnector();
		
		DefaultIoFilterChainBuilder chain = connector.getFilterChain();
//		chain.addFirst("C2ExecutorFilter", new ExecutorFilter());	
		chain.addLast("C2chain", new ProtocolCodecFilter(new ObjectSerializationCodecFactory()));
		InetSocketAddress socketAddress = new InetSocketAddress(bindPort);
		log.info("连接：" + socketAddress.getHostName() + ":" + socketAddress.getPort());
		connector.setDefaultRemoteAddress(socketAddress);
		connector.setHandler(new C2Handler());
		connector.setConnectTimeoutMillis(timeout);
		connector.getSessionConfig().setBothIdleTime(idleTime);
		return connector;
	}
	
	public static NioSocketConnector getConnector() {
		if(connector == null)
			connector = initConnector();
		return connector;
	}
	
	public static NioSocketConnector getConnector2() {
		return initConnector();
	}
	
}
