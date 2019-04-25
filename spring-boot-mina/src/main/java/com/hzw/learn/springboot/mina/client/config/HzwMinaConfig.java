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
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.hzw.learn.springboot.mina.client.config.ext.HzwIoHandler_long;
import com.hzw.learn.springboot.mina.client.config.ext.HzwSocketConnectFactory;
import com.hzw.learn.springboot.mina.client.config.ext.HzwSocketSessionFactory;

@Configuration
@EnableConfigurationProperties(HzwMinaProperties.class)
public class HzwMinaConfig {
	
	Logger log = LoggerFactory.getLogger("CONFIG");
	
	@Autowired
	HzwMinaProperties properties;
	
	@Autowired
	HzwSocketConnectFactory hzwSocketConnectFactory;
	
	@Bean
	public HzwSocketClientLong longSocketClient() throws Exception {
		HzwSocketClientLong socketClient = new HzwSocketClientLong();
		socketClient.setPool(connectionPool());
		return socketClient;
	}
	
//	@Bean
//	public HzwSocketClientShort shortSocketClient() throws Exception {
//		return new HzwSocketClientShort();
//	}
	
	@Bean
	public GenericObjectPool<IoSession> connectionPool() throws Exception {
		GenericObjectPool<IoSession> connectionPool = 
				new GenericObjectPool<IoSession>(sessionFactory());
		connectionPool.setMaxActive(properties.getMaxActive());
		connectionPool.setMaxIdle(properties.getMaxIdle());
		connectionPool.setMinIdle(properties.getMinIdle());
		connectionPool.setTestOnBorrow(true);	// 设置：获取对象时进行检查
		return connectionPool;
	}

	@Bean
	public BasePoolableObjectFactory<IoSession> sessionFactory() {
		return new HzwSocketSessionFactory(long_SocketConnector());
	}
	
	@Bean
	public NioSocketConnector long_SocketConnector() {
		return hzwSocketConnectFactory.newLongConnectot();
//		NioSocketConnector connector = new NioSocketConnector();
//		// 过滤器链
//		DefaultIoFilterChainBuilder chain = connector.getFilterChain();
////		connector.getFilterChain().addLast("loggingFilter", loggingFilter());
//		chain.addLast("en_de_Code_chain", new ProtocolCodecFilter(     
//                new ObjectSerializationCodecFactory()));
////		connector.getFilterChain().addFirst(name, filter);
////		connector.getFilterChain().addLast("xxx", IoFilter);
////		connector.getFilterChain().addAfter(baseName, name, filter);
////		connector.getFilterChain().addBefore(baseName, name, filter);
//		
//		log.info("连接：{}：{}，Timeout：{}，BothIdleTime：{}", properties.getHostName(), properties.getPort(), properties.getConnectTimeoutInMillis(), properties.getBothIdleTime());
//		connector.setDefaultRemoteAddress(new InetSocketAddress(properties.getHostName(), properties.getPort()));
//
//		connector.setHandler(ioHandler());
////		connector.setHandler(ServerHandler.getInstances());
//
//		// 连接超时时间 单位：毫秒
//		connector.setConnectTimeoutMillis(properties.getConnectTimeoutInMillis());
//		
//		// 闲置超时时间 单位：s
//		connector.getSessionConfig().setBothIdleTime(properties.getBothIdleTime());
//		return connector;
	}

	@Bean
	public LoggingFilter loggingFilter() {
		return new LoggingFilter();
	}

//	@Bean
//	public IoHandler ioHandler_long() {
//		return new HzwIoHandler_long();
//	}

}
