package com.hzw.learn.springboot.jvm.JIT;

import org.springframework.util.StopWatch;

import java.util.Timer;

public class JITDemo {
    static int stackDepth = 0;

    public static void main(String[] args) throws InterruptedException {
        for (int i = 0; i < 25; i++) {
            Thread.sleep(3000);
            long begin = System.currentTimeMillis();
            stackDepth = 0;
            try {
                sum(9877);
            } catch (StackOverflowError e){
                System.out.println("stackDepth:" + stackDepth);
            }
            long current = System.currentTimeMillis();
            System.out.println(i + ": " + (current - begin) + " ms");
        }
    }

    public static int sum(int n) {
        stackDepth++;
        if (n <= 1)
            return 1;
        int c=0;
        for (int i=0 ; i<n ; i++){
            c = c + i;
        }
        return n + sum(n - 1);
    }
}
