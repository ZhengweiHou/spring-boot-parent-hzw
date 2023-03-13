package com.hzw.learn.Test;

/**
 * @ClassName T2
 * @Description TODO
 * @Author houzw
 * @Date 2023/3/1
 **/
import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;

public class T3 {
    private static final String HEADER = "HTTP/1.1 200 OK\r\n" + "Content-type: text/html\r\n"
            + "Connection: close\r\n" + "\r\n";

    private static final int PORT = 8080;

    private static void exit(int status) {
        try {
            System.in.read();
        } catch (IOException e) {
        }
        System.exit(status);
    }

    private static void handle(Socket socket) {
        System.out.println(socket.getInetAddress() + ":" + socket.getPort());
        StringBuilder buffer = new StringBuilder();
        buffer.append(HEADER);
        buffer.append(T3.class.getClassLoader());
        try {
            socket.getOutputStream().write(buffer.toString().getBytes());
        } catch (IOException e) {
        } finally {
            try {
                socket.close();
            } catch (IOException e) {
            }
        }

    }

    public static void main(String[] args) throws IOException {
        int port;

        try {
            port = Integer.parseInt(args[0]);
        } catch (Exception e) {
            port = PORT;
        }

        final ServerSocket server = new ServerSocket();
        server.setReuseAddress(false);
        server.bind(new InetSocketAddress(port));

        // Terminator thread
        new Thread() {
            public void run() {
                try {
                    while (System.in.read() != 4);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                try {
                    server.close();
                } catch (IOException e) {
                }
                exit(0);
            }
        }.start();

        System.out.println("Listening on: " + port);
        Socket client = null;
        while (true) {
            try {
                client = server.accept();
                handle(client);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
