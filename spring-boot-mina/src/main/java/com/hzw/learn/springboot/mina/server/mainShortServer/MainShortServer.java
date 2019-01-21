package com.hzw.learn.springboot.mina.server.mainShortServer;

import java.io.IOException;
import java.net.InetSocketAddress;

import org.apache.mina.core.filterchain.DefaultIoFilterChainBuilder;
import org.apache.mina.core.session.IdleStatus;
import org.apache.mina.filter.codec.ProtocolCodecFilter;
import org.apache.mina.filter.codec.serialization.ObjectSerializationCodecFactory;
import org.apache.mina.transport.socket.SocketAcceptor;
import org.apache.mina.transport.socket.nio.NioSocketAcceptor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MainShortServer {
	
	private static Logger log = LoggerFactory.getLogger("MainShortServer");
	
    public static void main(String[] args) throws Exception {
    	log.info("短连接服务器启动》》》》》》");
    	MainShortServer.getInstances();
    }    
	
	private static MainShortServer mainShortServer = null;
	private SocketAcceptor acceptor = new NioSocketAcceptor();
	private DefaultIoFilterChainBuilder chain = acceptor.getFilterChain();
	private int bindPort = 8500;

	public static MainShortServer getInstances() {
		if (null == mainShortServer) {
			mainShortServer = new MainShortServer();
		}
		return mainShortServer;
	}

	private MainShortServer() {
		chain.addLast("myChin", new ProtocolCodecFilter(new ObjectSerializationCodecFactory()));
		acceptor.setHandler(ShortServerHandler.getInstances());
		acceptor.getSessionConfig().setReadBufferSize(2048);
		// 设置闲置超时时间
		acceptor.getSessionConfig().setIdleTime(IdleStatus.BOTH_IDLE, 3);

		try {
			InetSocketAddress socketAddress = new InetSocketAddress(bindPort);
			log.info("监听：" + socketAddress.getHostName() + ":" + socketAddress.getPort());
			acceptor.bind(new InetSocketAddress(bindPort));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
