package com.hzw.learn.springboot.mina.server.mainShortServer;

import java.io.IOException;
import java.net.InetSocketAddress;

import org.apache.mina.core.filterchain.DefaultIoFilterChainBuilder;
import org.apache.mina.core.session.IdleStatus;
import org.apache.mina.filter.codec.ProtocolCodecFilter;
import org.apache.mina.filter.codec.serialization.ObjectSerializationCodecFactory;
import org.apache.mina.filter.logging.LoggingFilter;
import org.apache.mina.transport.socket.SocketAcceptor;
import org.apache.mina.transport.socket.SocketSessionConfig;
import org.apache.mina.transport.socket.nio.NioSocketAcceptor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MainShortServer {
	
	private static Logger log = LoggerFactory.getLogger("MainShortServer");
	
	private static MainShortServer mainShortServer = null;
	private SocketAcceptor acceptor = null;
	private int bindPort = 8500;
	private int idleTime = 10;

	public static void main(String[] args) throws Exception {
		log.info("短连接服务器启动》》》》》》");
		new MainShortServer();
	}

	private MainShortServer() {
//		acceptor = new NioSocketAcceptor();	// 默认连接池默认实现：SimpleIoProcessorPool，池容量默认按CPU逻辑进程数有关
		acceptor = new NioSocketAcceptor(1); // 手动指定池数量
		DefaultIoFilterChainBuilder chain = acceptor.getFilterChain();
		chain.addLast("myChin", new ProtocolCodecFilter(new ObjectSerializationCodecFactory()));
		chain.addLast("logfilter", new LoggingFilter("xxxhouzw"));
//		acceptor.setHandler(ShortServerHandler.getInstances());
		acceptor.setHandler(new ShortServerHandler());

		SocketSessionConfig sessionConfig = acceptor.getSessionConfig();
		sessionConfig.setReadBufferSize(2048);

		// 设置闲置超时时间
		sessionConfig.setBothIdleTime(idleTime); // 默认和下行等价
//		sessionConfig.setIdleTime(IdleStatus.BOTH_IDLE, idleTime);

		try {
			InetSocketAddress socketAddress = new InetSocketAddress(bindPort);
			acceptor.bind(socketAddress);
			log.info("监听：" + socketAddress.getHostName() + ":" + socketAddress.getPort());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
