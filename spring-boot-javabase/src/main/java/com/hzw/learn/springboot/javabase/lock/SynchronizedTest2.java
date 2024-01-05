package com.hzw.learn.springboot.javabase.lock;

import java.util.HashMap;
import java.util.Map;

/**
 * @ClassName SynchronizedTest2
 * @Description TODO
 * @Author houzw
 * @Date 2023/9/7
 **/
public class SynchronizedTest2 {

    public static Object writeLock = new Object();
    public static volatile Boolean writeBlock = false;

    public static void main(String[] args) throws InterruptedException {

        Thread t1 = new Thread(() -> {
            System.out.println("0");
            synchronized (writeLock){
                System.out.println("1");
                try {
                    writeLock.wait(3000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                System.out.println("2");
            }
        });

        Thread t2 = new Thread(() -> {

        });

    }


}
