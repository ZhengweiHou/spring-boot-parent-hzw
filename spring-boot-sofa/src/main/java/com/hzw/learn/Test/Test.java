package com.hzw.learn.Test;

import java.io.IOException;
import java.lang.management.ManagementFactory;
import java.net.InetSocketAddress;
import java.net.ServerSocket;

/**
 * @ClassName Test
 * @Description TODO
 * @Author houzw
 * @Date 2023/3/1
 **/
public class Test {
    public static void main(String[] args) throws Exception {

        String processId = ManagementFactory.getRuntimeMXBean().getName().split("@")[0];
        System.out.println("processId:" + processId);

        ServerSocket s1 = new ServerSocket();
        s1.bind(new InetSocketAddress("0.0.0.0", 12200));
        System.out.println(s1.getLocalPort());

        ServerSocket s2 = new ServerSocket();
        s2.bind(new InetSocketAddress("0.0.0.0", 12200));
        System.out.println(s2.getLocalPort());

        new ServerSocket(12200);
        new ServerSocket(12200);


        Thread.sleep(100000);
    }
}
