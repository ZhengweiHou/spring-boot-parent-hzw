package com.hzw.learn.springboot.mina.scin1.s3;

import java.io.IOException;
import java.net.InetSocketAddress;

import org.apache.mina.core.filterchain.DefaultIoFilterChainBuilder;
import org.apache.mina.core.session.IdleStatus;
import org.apache.mina.filter.codec.ProtocolCodecFilter;
import org.apache.mina.filter.codec.serialization.ObjectSerializationCodecFactory;
import org.apache.mina.transport.socket.SocketAcceptor;
import org.apache.mina.transport.socket.SocketSessionConfig;
import org.apache.mina.transport.socket.nio.NioSocketAcceptor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class S3 {
	private static Logger log = LoggerFactory.getLogger("S3");
	
	private int bindPort = 8503;
	
	private SocketAcceptor acceptor = new NioSocketAcceptor();
	private DefaultIoFilterChainBuilder chain = acceptor.getFilterChain();
	
	public S3() {
		chain.addLast("s3Chain", new ProtocolCodecFilter(new ObjectSerializationCodecFactory()));
		acceptor.setHandler(new S3Handler());
		SocketSessionConfig config = acceptor.getSessionConfig();
		config.setMaxReadBufferSize(2048);
		config.setIdleTime(IdleStatus.BOTH_IDLE, 1000);
		
		InetSocketAddress socketAddress = new InetSocketAddress(bindPort);
		
		log.info("监听：" + socketAddress.getHostName() + ":" + socketAddress.getPort());
		
		try {
			acceptor.bind(socketAddress);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
}
