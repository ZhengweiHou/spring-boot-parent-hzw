package com.hzw.learn.springboot.jvm.tests;

import sun.misc.GThreadHelper;

import java.util.ArrayList;

public class stacktest {


    public static void main(String[] args) throws InterruptedException {


        ArrayList<NodeTest> nodes = new ArrayList<NodeTest>();

        for (int i=0; i < 10000000; i++){
            Thread.sleep(5);
            System.out.println(i);
            nodes.add(new NodeTest());

        }
    }


    static class NodeTest{
        byte[] a = new byte[5*1024];    // 不会被GC回收
        public NodeTest(){
            byte[] bytes = new byte[12 * 1024];
        }   //构造方法执行完，会被GC回收
    }

}