package com.hzw.learn.springboot.javabase;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Set;

/**
 * @ClassName NioWithSelectorServer
 * @Description 非阻塞IO TCP 服务 使用多路复用器处理案例
 * @Author houzw
 * @Date 2024/7/21
 **/
public class NioWithSelectorServer {

    public static void main(String[] args) throws IOException {
        // 创建NIO  server socket channel
        ServerSocketChannel sSocket = ServerSocketChannel.open();
        // 绑定端口，此时该进程就已经占用端口了
        sSocket.socket().bind(new InetSocketAddress(9999));
        // 设置ScoketChanel为非阻塞
        sSocket.configureBlocking(false);

        // 创建多路复用器
        Selector selector = Selector.open();
        // ServerSocketChannel 注册到 selector多路复用器上，并指定selector对channel的accept操作感兴趣
        sSocket.register(selector, SelectionKey.OP_ACCEPT);

        System.out.println("服务已启动");

        ServerSocketChannel sSocket2 = ServerSocketChannel.open();
        sSocket2.socket().bind(new InetSocketAddress(8888));
        sSocket2.configureBlocking(false);
        sSocket2.register(selector, SelectionKey.OP_ACCEPT);

        while(true){
            // 多路复用器阻塞等待感兴趣的事件发生
            int size = selector.select();
            System.out.printf("selector监听的事件数量：%d\n", size);

            Set<SelectionKey> selectionKeys = selector.selectedKeys();
            Iterator<SelectionKey> iter = selectionKeys.iterator();

            while (iter.hasNext()){
                SelectionKey key = iter.next();

                if(key.isAcceptable()){
                    // 处理多路复用器查询到的OP_ACCEPT事件 TODO 为什么每次消息发送都会从多路复用器中获取到accept事件？
                    ServerSocketChannel sSocketChan = (ServerSocketChannel) key.channel();
                    System.out.printf("[%d]accept事件处理\n", sSocketChan.socket().getLocalPort());
                    SocketChannel cSocket = sSocketChan.accept();
                    if(cSocket != null) {
                        cSocket.configureBlocking(false);

                        // 将socket连接channel也接入到多路复用器中统一监控，并指定感兴趣的监听事件为OP_READ
                        cSocket.register(selector, SelectionKey.OP_READ);

                        System.out.printf("客户端连接成功，%d:%d\n", cSocket.socket().getPort(), cSocket.socket().getLocalPort());
                    }
                }else if (key.isReadable()){
                    // 处理多路复用器查询到的OP_READ事件
                    SocketChannel sc = (SocketChannel) key.channel();

                    ByteBuffer bbf = ByteBuffer.allocate(8);

                    // SocketChannel 也要设置成非阻塞的，否则此处会阻塞
                    int len = sc.read(bbf);
                    if (len == -1) {
                        System.out.printf("连接[%d]已关闭\n",sc.socket().getPort());
                        // 取消多路复用器中的监听
                        sc.keyFor(selector).cancel();
                    } else if (len > 0) {
                        System.out.printf("接受到[%d:%d]的消息：%s\n", sc.socket().getPort(), sc.socket().getLocalPort(), new String(bbf.array(),0,len));
                        sc.write(ByteBuffer.wrap(("Server.port:" + sc.socket().getLocalPort() + "\n").getBytes()));
                    }
                }
            }
        }
    }
}
