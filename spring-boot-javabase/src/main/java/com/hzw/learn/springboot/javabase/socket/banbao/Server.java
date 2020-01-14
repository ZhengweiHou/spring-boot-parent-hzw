package com.hzw.learn.springboot.javabase.socket.banbao;



import org.apache.tomcat.util.buf.ByteChunk;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {

    public static void main(String[] args) {
        try {
        	Server server = new Server();
            server.setUpServer(9292);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void setUpServer(int port) throws IOException {
    	System.out.println("监听：" + port);
        ServerSocket server = new ServerSocket(port);
        while (true) {
            Socket client = server.accept();
            System.out.println("客户端IP：" + client.getRemoteSocketAddress());
            processMesage(client);
        }
    }

    private void processMesage(Socket client) throws IOException {
        InputStream ins = client.getInputStream();

        ByteArrayOutputStream byteOut = new ByteArrayOutputStream();

        byte[] b = new byte[client.getReceiveBufferSize()]; // 缓冲字节数组大小设置成当前socket tcp窗口大小

        int readNumber;

        while (true){
            readNumber = ins.read(b);

            if(readNumber == -1){  // -1 代表socket连接已断开，在连接断开前会一直尝试从当前socket 流中获取字节流
                break;
            }

            byteOut.write(b,0,readNumber);  // 将当前缓存字节数组里的数据取出到ByteArrayOutputStream中
        }

        byte[] resultByteArray = byteOut.toByteArray();

        System.out.println(new String(resultByteArray,"utf-8"));
//        FIXME 获取的报文需要根据报文约定格式校验完整性
//        FIXME 同时此处没有处理粘包问题，短连接没有粘包问题，若此处socket是长连接就可能发生粘包问题
//        FIXME 定义的缓冲字节数组可能过大

//        TODO 是否可以尝试使用nio优化上述操作

    }
}