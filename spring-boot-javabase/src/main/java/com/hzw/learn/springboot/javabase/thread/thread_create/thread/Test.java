package com.hzw.learn.springboot.javabase.thread.thread_create.thread;

/**
 * @ClassName Test
 * @Description TODO
 * @Author houzw
 * @Date 2023/3/3
 **/
public class Test extends Thread {

    @Override
    public void run() {
        double count = 0;
        for (int i=0; i<1000000 ;i++){
            count += i;
        }
    }

    public static void main(String[] args) throws InterruptedException {
        for (int j=0; j<100; j++){
            new Test().start();
        }

        Thread.sleep(10000);
    }
}
