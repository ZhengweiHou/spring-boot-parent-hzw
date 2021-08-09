package com.hzw.learn.springboot.jvm.helloword;


import java.util.Random;

public class HighCpu {
    static int threadNum = 5;
    public static void main(String[] args) {
        for(int y=1; y <= threadNum; y++) {
            new Thread(() -> {
                while (true) {
                    int i = (int) (Math.random() * 1000 + 1000);
                    int m = i;
                    int n = 0;
                    for (; m > 0; ) {
                        n += m;
                        m--;
                    }
//                    System.out.println("" + i + "==>" + n);

                }
            }).start();

        }
    }
}
