package com.hzw.learn.springboot.javabase.socket.banbao;



import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;

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
        
        System.out.println(client.getReceiveBufferSize());
        
        byte[] b = new byte[client.getReceiveBufferSize()];
        
        int readNumber = ins.read(b);
        
        System.out.println(readNumber);
        
        String msg = new String(b,"utf-8");
        
        
        System.out.println(msg);
        
        
        
        
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