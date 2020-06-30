package com.hzw.learn.springboot.netty.bio;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Date;
import java.util.Random;


public class TimeServer {

	public static void main(String[] args) throws IOException, InterruptedException {
		int port = 8080;
		ServerSocket server = null;
		try {
			server = new ServerSocket(port);
			System.out.println("服务端监听端口：" + port);
			
			Socket socket = null;
			
			while (true) {
				socket = server.accept();
				Thread.sleep((long)(Math.random() * 100));
				
				new Thread(new TimeServerHandler(socket)).start();
			}
			
		} finally {
			if (server != null) {
				System.out.println("服务端关闭");
				server.close();
				server = null;
			}
		}
		
	}

}

class TimeServerHandler implements Runnable {

	private Socket socket;

	public TimeServerHandler(Socket socket) {
		this.socket = socket;
	}

	@Override
	public void run() {

		BufferedReader reader = null;
		PrintWriter writer = null;

		try {
			reader = new BufferedReader(new InputStreamReader(this.socket.getInputStream()));
			writer = new PrintWriter(this.socket.getOutputStream(), true);
			
			String msg = null;
			String currentTime = null;
			
			while (true) {
				msg = reader.readLine();
				if (msg == null) {
					break;
				}
				
				System.out.println(Thread.currentThread().getId() +"服务端收到消息：" + msg);

//				currentTime = new Date(System.currentTimeMillis()).toString();
				currentTime = "" + System.currentTimeMillis();
				
				writer.println(currentTime);
			}
			
		} catch (IOException e) {
			if (reader != null) {
				try {
					reader.close();
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			}
			
			if (writer != null) {
				writer.close();
			}
			
			if (this.socket != null) {
				try {
					this.socket.close();
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			}
			
			this.socket = null;
			
		}
	}

}
