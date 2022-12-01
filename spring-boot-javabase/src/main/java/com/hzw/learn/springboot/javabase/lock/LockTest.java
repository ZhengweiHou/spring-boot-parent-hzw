package com.hzw.learn.springboot.javabase.lock;

import org.junit.Test;

import java.util.Date;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class LockTest {
    static Lock fairLock = new ReentrantLock(true);     // 公平锁
    static Lock nonfairLock = new ReentrantLock(false); // 非公平锁
    static Lock defaultLock = new ReentrantLock();            // 默认非公平锁

    public static void main(String[] args) {
        ScheduledExecutorService es = Executors.newScheduledThreadPool(5);

//        es.execute(new LockRunnable(fairLock));
//        es.execute(new LockRunnable(fairLock));
        es.execute(new LockRunnable(nonfairLock));
        es.execute(new LockRunnable(nonfairLock));
//        es.execute(new LockRunnable(defaultLock));
    }

    @Test
    public void fairLockTest() throws InterruptedException {
        new Thread(new LockRunnable(fairLock),"fairLock_thread1").start();
        new Thread(new LockRunnable(fairLock),"fairLock_thread2").start();
        Thread.sleep(10 * 1000);
    }

    @Test
    public void nonfairLock() throws InterruptedException {
        new Thread(new LockRunnable(nonfairLock),"nonfairLock_thread1").start();
        new Thread(new LockRunnable(nonfairLock),"nonfairLock_thread2").start();
        Thread.sleep(10 * 1000);
    }



    static class LockRunnable implements Runnable{
        Lock locktemp;

        public LockRunnable(Lock lock){
            locktemp = lock;
        }

        @Override
        public void run() {
            while(true) {
                try {
                    locktemp.lock();
                    System.out.println(Thread.currentThread().getName()  + "正在持有锁");
                    Thread.sleep(1000);
                } catch (Exception e) {} finally {
                    System.out.println(Thread.currentThread().getName()  + "释放了锁");
                    locktemp.unlock();
                }
            }
        }
    }
}
