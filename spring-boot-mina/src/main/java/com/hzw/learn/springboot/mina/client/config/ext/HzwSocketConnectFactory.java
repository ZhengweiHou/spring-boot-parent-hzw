package com.hzw.learn.springboot.mina.client.config.ext;

import java.net.InetSocketAddress;

import org.apache.commons.pool.BasePoolableObjectFactory;
import org.apache.mina.core.filterchain.DefaultIoFilterChainBuilder;
import org.apache.mina.core.future.ConnectFuture;
import org.apache.mina.core.future.WriteFuture;
import org.apache.mina.core.service.IoHandlerAdapter;
import org.apache.mina.core.session.IoSession;
import org.apache.mina.filter.codec.ProtocolCodecFilter;
import org.apache.mina.filter.codec.serialization.ObjectSerializationCodecFactory;
import org.apache.mina.transport.socket.nio.NioSocketConnector;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.hzw.learn.springboot.mina.client.config.HzwMinaProperties;

@Component
public class HzwSocketConnectFactory {
	Logger log = LoggerFactory.getLogger("CONFACTORY");
	
	@Autowired
	HzwMinaProperties properties;
	
	@Autowired
	HzwIoHandler_long ioHandler_long;
	
	@Autowired
	HzwIoHandler_short ioHandler_short;
	
	public NioSocketConnector newShortConnectot() {
		return newConnectot(ioHandler_short);
	}
	
	public NioSocketConnector newLongConnectot() {
		return newConnectot(ioHandler_long);
	}
	
	public NioSocketConnector newConnectot(IoHandlerAdapter ioHandler) {
		NioSocketConnector connector = new NioSocketConnector();
		// 过滤器链
		DefaultIoFilterChainBuilder chain = connector.getFilterChain();
//		connector.getFilterChain().addLast("loggingFilter", loggingFilter());
		chain.addLast("en_de_Code_chain", new ProtocolCodecFilter(     
                new ObjectSerializationCodecFactory()));
//		connector.getFilterChain().addFirst(name, filter);
//		connector.getFilterChain().addLast("xxx", IoFilter);
//		connector.getFilterChain().addAfter(baseName, name, filter);
//		connector.getFilterChain().addBefore(baseName, name, filter);
		
		log.info("连接：{}：{}，Timeout：{}，BothIdleTime：{}", properties.getHostName(), properties.getPort(), properties.getConnectTimeoutInMillis(), properties.getBothIdleTime());
		connector.setDefaultRemoteAddress(new InetSocketAddress(properties.getHostName(), properties.getPort()));

		connector.setHandler(ioHandler);
//		connector.setHandler(ServerHandler.getInstances());

		// 连接超时时间 单位：毫秒
		connector.setConnectTimeoutMillis(properties.getConnectTimeoutInMillis());
		
		// 闲置超时时间 单位：s
		connector.getSessionConfig().setBothIdleTime(properties.getBothIdleTime());
		return connector;
	}
	
}
