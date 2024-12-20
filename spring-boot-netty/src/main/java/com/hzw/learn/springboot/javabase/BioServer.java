package com.hzw.learn.springboot.javabase;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * @ClassName BioServer
 * @Description 阻塞IO tcp服务端测试
 * @Author houzw
 * @Date 2024/7/20
 **/
public class BioServer {

    public static void main(String[] args) throws IOException {
        ServerSocket sSocket = new ServerSocket(9999);
        while(true){
            System.out.println("waiting connection...");

            // 阻塞,等待连接接入
            Socket cSocket = sSocket.accept();

            System.out.printf("连接接入，%d:%d\n",cSocket.getPort(),cSocket.getLocalPort());

            byte[] bytes = new byte[1024];

            while(true){
                // 阻塞，等待客户端输入
                int readlen = cSocket.getInputStream().read(bytes);
                if(readlen == -1){
                    System.out.println("连接结束");
                    break;
                }else{
                    System.out.printf("接收到消息，长度:%d,msg:%s\n",readlen,new String(bytes,0,readlen));
                }
            }
        }
    }

}
