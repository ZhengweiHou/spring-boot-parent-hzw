package com.hzw.learn.springboot.netty.bio;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class TimeClient {
	public static void main(String[] args) throws IOException {
		int port = 8888;

		for (int i = 0; i < 10; i++) {
//			new Thread(() -> {
			Socket socket = null;
			BufferedReader in = null;
			PrintWriter out = null;

			try {
				socket = new Socket("127.0.0.1", port);
				in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
				out = new PrintWriter(socket.getOutputStream(), true);

				out.println("I'm client" + Thread.currentThread().getId() + "!!");
				String resp = in.readLine();
				System.out.println(Thread.currentThread().getId() + "Now is: " + resp);
			} catch (IOException e) {
			} finally {
				if (in != null) {
					try {
						in.close();
					} catch (IOException e) {
						e.printStackTrace();
					}
				}

				if (out != null) {
					out.close();
				}

				if (socket != null) {
					try {
						socket.close();
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
				socket = null;
			}
//			}).start();
		}
	}
}
