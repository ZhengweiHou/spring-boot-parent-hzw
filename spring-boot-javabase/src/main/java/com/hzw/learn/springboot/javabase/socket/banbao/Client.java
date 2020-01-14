package com.hzw.learn.springboot.javabase.socket.banbao;

import java.io.IOException;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.nio.charset.Charset;

public class Client {
	
	String SERVER_IP="0.0.0.0";
	int SERVER_PORT=9292;

    public static void main(String[] args) {
    	Client client = new Client();
    	client.sendTest();
    	
    }
    
    
    public void sendTest() {
    	this.send("hello ni hao!!");
    }
    
    public void send(String msg) {
    	InetSocketAddress addr = new InetSocketAddress(SERVER_IP, SERVER_PORT);
    	
    	Socket socket = new Socket();
    	
    	try {
    		
			socket.connect(addr,5000);
			socket.setSoTimeout(5000);
			
			OutputStream out = socket.getOutputStream();

			out.write(getMessage(msg));

			out.write("这是第二个包".getBytes("utf-8")); // 模拟分包发送第二个包
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	
    	
    }
    
    
    
    public byte[] getMessage(String data) throws UnsupportedEncodingException {
    	
    	
//    	String data = "hello ni hao!!!";
    	
    	long len = data.getBytes("utf-8").length;
    	len +=4;
    	
    	String head = String.format("%05d", len);
    	
    	long hlen = head.getBytes("utf-8").length;
    	
    	System.out.println("head[" + hlen + ":" + head + "]");
    	System.out.println("data[" + len + ":" + data + "]");
    	
    	String msg = head + data;
    	
    	System.out.println("sendmsg:" + msg);
    	
    	return msg.getBytes("utf-8");
	}
    
	
}
