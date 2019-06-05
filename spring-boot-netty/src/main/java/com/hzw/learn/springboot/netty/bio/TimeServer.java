package com.hzw.learn.springboot.netty.bio;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class TimeServer {

	public static void main(String[] args) throws IOException {
		int port = 8080;
		ServerSocket server = null;
		try {
			server = new ServerSocket(port);
			System.out.println("服务端监听端口：" + port);
			
			Socket socket = null;
			
			while (true) {
				socket = server.accept();
				new Thread(() -> {
					socket.getInputStream()
				} ).start();
			}
			
		} finally {
			
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
				
			}
			
			
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
