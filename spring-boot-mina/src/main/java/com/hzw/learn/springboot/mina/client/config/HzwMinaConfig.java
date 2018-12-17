package com.hzw.learn.springboot.mina.client.config;

import java.net.InetSocketAddress;

import org.apache.commons.pool.BasePoolableObjectFactory;
import org.apache.commons.pool.impl.GenericObjectPool;
import org.apache.mina.core.filterchain.DefaultIoFilterChainBuilder;
import org.apache.mina.core.service.IoHandler;
import org.apache.mina.core.session.IoSession;
import org.apache.mina.filter.codec.ProtocolCodecFilter;
import org.apache.mina.filter.codec.serialization.ObjectSerializationCodecFactory;
import org.apache.mina.filter.logging.LoggingFilter;
import org.apache.mina.transport.socket.nio.NioSocketConnector;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.hzw.learn.springboot.mina.client.config.ext.HzwIoHandler;
import com.hzw.learn.springboot.mina.client.config.ext.HzwSocketConnectionFactory;

@Configuration
public class HzwMinaConfig {
	@Value("${hzwmina.socketHostName?:localhost}")
	String hostName;
	@Value("${hzwmina.socketPort?:8081}")
	Integer port;
	@Value("${hzwmina.connectTimeoutInMillis?:5000}")
	Long connectTimeoutInMillis;
	
	@Bean
	public HzwSocketClient socketClient() throws Exception {
		HzwSocketClient socketClient = new HzwSocketClient();
		socketClient.setPool(connectionPool());
		return socketClient;
	}
	
	@Bean
	public GenericObjectPool<IoSession> connectionPool() throws Exception {
		GenericObjectPool<IoSession> connectionPool = 
				new GenericObjectPool<IoSession>(connectionFactory());
		connectionPool.setMaxActive(10);
		connectionPool.setMaxIdle(10);
		connectionPool.setMinIdle(5);
		return connectionPool;
	}

	@Bean
	public BasePoolableObjectFactory<IoSession> connectionFactory() {
		return new HzwSocketConnectionFactory(socketConnector());
	}
	
	@Bean
	public NioSocketConnector socketConnector() {
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
		
		System.out.println("服务端地址配置：" + hostName + ":" + port);
		connector.setDefaultRemoteAddress(new InetSocketAddress(hostName, port));

		connector.setHandler(ioHandler());
//		connector.setHandler(ServerHandler.getInstances());

		// 连接超时时间 单位：毫秒
		connector.setConnectTimeoutMillis(connectTimeoutInMillis);
		
		// 闲置超时时间 单位：s
		connector.getSessionConfig().setBothIdleTime(2);
		return connector;
	}

	@Bean
	public LoggingFilter loggingFilter() {
		return new LoggingFilter();
	}

	@Bean
	public IoHandler ioHandler() {
		return new HzwIoHandler();
	}

}
