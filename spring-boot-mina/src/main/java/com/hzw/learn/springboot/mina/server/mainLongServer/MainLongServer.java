package com.hzw.learn.springboot.mina.server.mainLongServer;

import java.io.IOException;
import java.net.InetSocketAddress;

import org.apache.mina.core.filterchain.DefaultIoFilterChainBuilder;
import org.apache.mina.filter.codec.ProtocolCodecFilter;
import org.apache.mina.filter.codec.serialization.ObjectSerializationCodecFactory;
import org.apache.mina.transport.socket.SocketAcceptor;
import org.apache.mina.transport.socket.nio.NioSocketAcceptor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.hzw.learn.springboot.mina.client.config.ext.HzwIoHandler_long;

public class MainLongServer {     
	private static Logger log = LoggerFactory.getLogger("MainLongServer");
	
    public static void main(String[] args) throws Exception {
    	log.info("长连接服务器启动》》》》》》");
    	MainLongServer.getInstances();
    }    
	
    private static MainLongServer mainServer = null;     
    private SocketAcceptor acceptor = new NioSocketAcceptor();     
    private DefaultIoFilterChainBuilder chain = acceptor.getFilterChain();     
    private int bindPort = 8500;     
    
    
    public static MainLongServer getInstances() {     
        if (null == mainServer) {     
            mainServer = new MainLongServer();     
        }     
        return mainServer;     
    }     
    
    private MainLongServer() {     
        chain.addLast("myChin",
        		new ProtocolCodecFilter(     
        				new ObjectSerializationCodecFactory()
        		)
        );     
        acceptor.setHandler(ServerHandler.getInstances());
        acceptor.getSessionConfig().setReadBufferSize(2048);
        try {
        	InetSocketAddress socketAddress = new InetSocketAddress(bindPort);
//        	InetSocketAddress socketAddress = new InetSocketAddress("localhost", bindPort);
        	log.info("监听：" + socketAddress.getHostName() + ":" + socketAddress.getPort());
            acceptor.bind(socketAddress);     
        } catch (IOException e) {     
            e.printStackTrace();     
        }     
    }     
    
 
}   
