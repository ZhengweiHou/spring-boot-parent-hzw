package com.hzw.learn.springboot.javabase.socket.banbao;



import org.apache.tomcat.util.buf.ByteChunk;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.ByteBuffer;

public class Server {
    // 缓存一个read事件中一个不完整的包，以待下次read事件到来时拼接成完整的包
    StringBuilder StringCacheBuffer = new StringBuilder();
    boolean cache = false;

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

        byte[] b = new byte[client.getReceiveBufferSize()]; // 缓冲字节数组大小设置成当前socket tcp

        int readNumber;

        while (true){
            readNumber = ins.read(b);

            if(readNumber == -1){  // -1 代表socket连接已断开，在连接断开前会一直尝试从当前socket 流中获取字节流
                break;
            }

            byteOut.write(b,0,readNumber);
        }

        byte[] resultByteArray = byteOut.toByteArray();

        System.out.println(new String(resultByteArray,"utf-8"));

        
        
        
        
//        DataInputStream dins = new DataInputStream(ins);
//        int bodyLen = -1;
//        // 服务端解包过程
//        byte[] data = new byte[10];
//        byte[] cacheBuffer = null;
//        byte flag = dins.readByte();
//        int totalLen = dins.readInt();
//        // 读取第一次获得的包消息长度
//        int count=0;
//        bodyLen = totalLen - 5;
//        System.out.println("消息总长度" + bodyLen);
//        try{
//        while (true) {
//            // 还没有读出包头，先读出包头
//            if (bodyLen == -1) {
//                if (bodyLen > data.length) {
//                    cache = true;
//                } else {
//                    data = new byte[bodyLen];
//                    dins.readFully(data);
//                    String msgs = new String(data);
//                    System.out.println("发来的内容是1:" +StringCacheBuffer.toString()+msgs);
//                }
//            } else {
//                if (data.length >= bodyLen) {
//                    byte[] datas = new byte[bodyLen];
//                    dins.readFully(datas);
//                    String msgs = new String(datas);
//                    System.out.println("发来的内容是2:" +StringCacheBuffer.toString()+msgs);
//                } else {
//                    cache = true;
//                }
//            }
//            if (cache) {
//                cacheBuffer = new byte[10];
//                dins.readFully(cacheBuffer);
//                StringCacheBuffer.append(new String(cacheBuffer));
//                count=count+10;
//            }
//            if(bodyLen-count<=data.length){
//                bodyLen=bodyLen-count;
//            }
//        }
//        }catch(Exception e){
//            e.printStackTrace();
//        }
//        StringCacheBuffer=new StringBuilder();
    }
}