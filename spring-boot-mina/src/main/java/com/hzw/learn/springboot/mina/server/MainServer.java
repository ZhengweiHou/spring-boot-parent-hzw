package com.hzw.learn.springboot.mina.server;

import java.io.IOException;
import java.net.InetSocketAddress;

import org.apache.mina.core.filterchain.DefaultIoFilterChainBuilder;
import org.apache.mina.filter.codec.ProtocolCodecFilter;
import org.apache.mina.filter.codec.serialization.ObjectSerializationCodecFactory;
import org.apache.mina.transport.socket.SocketAcceptor;
import org.apache.mina.transport.socket.nio.NioSocketAcceptor;

import com.hzw.learn.springboot.mina.client.config.ext.HzwIoHandler;

public class MainServer {     
	
    public static void main(String[] args) throws Exception {
    	System.out.println("服务器启动》》》》》》");
        MainServer.getInstances();
    }    
	
    private static MainServer mainServer = null;     
    private SocketAcceptor acceptor = new NioSocketAcceptor();     
    private DefaultIoFilterChainBuilder chain = acceptor.getFilterChain();     
    private int bindPort = 8500;     
    
    
    public static MainServer getInstances() {     
        if (null == mainServer) {     
            mainServer = new MainServer();     
        }     
        return mainServer;     
    }     
    
    private MainServer() {     
        chain.addLast("myChin", new ProtocolCodecFilter(     
                new ObjectSerializationCodecFactory()));     
        acceptor.setHandler(ServerHandler.getInstances()); 
//        acceptor.setHandler(new HzwIoHandler());
        try {
        	InetSocketAddress socketAddress = new InetSocketAddress(bindPort);

//        	InetSocketAddress socketAddress = new InetSocketAddress("localhost", bindPort);
        	System.out.println("监听：" + socketAddress.getHostName() + ":" + socketAddress.getPort());
            acceptor.bind(socketAddress);     
        } catch (IOException e) {     
            e.printStackTrace();     
        }     
    }     
    
 
}   
