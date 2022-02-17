package com.hzw.learn.springboot.javabase.lock;

import java.util.Date;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class LockTest {
    static Lock lock = new ReentrantLock();
    public static void main(String[] args) {
        ScheduledExecutorService es = Executors.newScheduledThreadPool(5);
        es.execute(new LockRunnable());
        es.execute(new LockRunnable());
        es.execute(new LockRunnable());
        es.execute(new LockRunnable());
        es.execute(new LockRunnable());
        es.execute(new LockRunnable());
    }


    static class LockRunnable implements Runnable{

        @Override
        public void run() {
            while(true) {
//                try {
//                    lock.tryLock(1, TimeUnit.SECONDS);
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }
                try {
                    Thread.sleep(3000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("" + Thread.currentThread().getId() + new Date());
            }
        }
    }
}
