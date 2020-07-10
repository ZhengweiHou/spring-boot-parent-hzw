package com.hzw.learn.springboot.jvm.Volatile;

/**
 * @ClassName VolatileTest
 * @Description TODO
 * @Author houzw
 * @Date 2020/7/9
 **/
public class VolatileTest {
    public static volatile int pen=0;

    public static void main(String[] args) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("秀才等书童送笔过来...");
                while (0 == pen){}
                System.out.println("书童笔送来了，开始作诗...");
            }
    },"秀才").start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("我是书童，我在找笔...");
                try {Thread.sleep(2000);}
                catch (InterruptedException e) {e.printStackTrace();}
                System.out.println("找到笔了，送过去...");
                sendPen();
            }
        },"书童").start();
    }

    public  static void sendPen(){
        pen = 1;
    }
}


