package com.hzw.learn.springboot.javabase.lock;

import org.junit.Test;

import java.util.Date;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ReentrantLockTest {
    static Lock fairLock = new ReentrantLock(true);     // 公平锁
    static Lock nonfairLock = new ReentrantLock(false); // 非公平锁
    static Lock lock = new ReentrantLock();            // 默认非公平锁



    @Test
    public void fairLockTest() throws InterruptedException {
        new Thread(new LockRunnable(fairLock),"fairLock_thread1").start();
        new Thread(new LockRunnable(fairLock),"fairLock_thread2").start();
        Thread.sleep(10 * 1000);
    }

    @Test
    public void nonfairLockTest() throws InterruptedException {
        new Thread(new LockRunnable(nonfairLock),"nonfairLock_thread1").start();
        new Thread(new LockRunnable(nonfairLock),"nonfairLock_thread2").start();
        Thread.sleep(10 * 1000);
    }

    @Test
    public void mutiThreadLocktest() throws InterruptedException {
        CountDownLatch start = new CountDownLatch(1);
        CountDownLatch end = new CountDownLatch(2);

        Runnable r1 = new Runnable() {
            @Override
            public void run() {
                try {
                    start.await();
                    lock.lock();
                    System.out.println(Thread.currentThread().getName() + "正在持有锁");
                    Thread.sleep(2000);
                } catch (Exception e) { } finally {
                    System.out.println(Thread.currentThread().getName() + "释放了锁");
                    lock.unlock();
                }
                end.countDown();
            }
        };
        new Thread(r1,"th1").start();
        new Thread(r1,"th2").start();
        Thread.sleep(500);
        start.countDown();
        end.await();
    }

    @Test
    public void tryLockTest() throws InterruptedException {
        Runnable runer = new Runnable() {
            @Override
            public void run() {
                while (true) {
                    try {
                        if (lock.tryLock(2, TimeUnit.SECONDS)) {
                            log("正在持有锁");
                            Thread.sleep(3000);
                            log("释放了锁");
                            lock.unlock();
                        } else {
                            log("尝试获取锁失败");
                        }
                    } catch (Exception e) {
                    }
                }
            }
        };
        new Thread(runer,"th1").start();
        new Thread(runer,"th2").start();

        Thread.sleep(100 * 1000);

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


    public static void log(String msg){
        System.out.println("" + new Date() + Thread.currentThread().getName()  + msg);
    }
}
