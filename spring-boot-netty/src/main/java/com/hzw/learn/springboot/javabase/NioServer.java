package com.hzw.learn.springboot.javabase;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.ArrayList;
import java.util.Iterator;

/**
 * @ClassName NioServer
 * @Description 非阻塞IO tcp 服务端测试
 * @Author houzw
 * @Date 2024/7/20
 **/
public class NioServer {
    public static void main(String[] args) throws IOException, InterruptedException {
        ArrayList<SocketChannel> cSocketList = new ArrayList<SocketChannel>();

        // 创建NIO socket channel
        ServerSocketChannel sSocket = ServerSocketChannel.open();
        // 绑定端口，此时该进程就已经占用端口了
        sSocket.socket().bind(new InetSocketAddress(9999));

        // 设置ScoketChanel为非阻塞
        sSocket.configureBlocking(false);
        System.out.println("服务已启动");

        while(true){
            Thread.sleep(10); // 非阻塞时，该线程循环会占用大量cpu资源
            // 尝试获取客户端的请求连接，ServerSocketChannel若为非阻塞的此处不会阻塞，否则会被阻塞
            SocketChannel cSocket = sSocket.accept();
            if(cSocket != null){
                System.out.printf("新客户端连接成功: %d:%d\n", cSocket.socket().getPort(), cSocket.socket().getLocalPort());
                // 设置连接套接字也为非阻塞的，否则下面read数据时会阻塞，直到客户端发送了数据
                cSocket.configureBlocking(false);

                cSocketList.add(cSocket);
            }

            Iterator<SocketChannel> csiter = cSocketList.iterator();
            while (csiter.hasNext()) {
                SocketChannel sc = csiter.next();
                ByteBuffer bbf = ByteBuffer.allocate(8);

                // SocketChannel 也要设置成非阻塞的，否则此处会阻塞
                int len = sc.read(bbf);
                if (len == -1) {
                    System.out.printf("连接[%d]已关闭\n",sc.socket().getPort());
                    csiter.remove();
                    continue;
                } else if (len > 0) {
                    System.out.printf("接受到[%d]的消息：%s\n", sc.socket().getPort(), new String(bbf.array(),0,len));
                }
            }
        }

    }
}
