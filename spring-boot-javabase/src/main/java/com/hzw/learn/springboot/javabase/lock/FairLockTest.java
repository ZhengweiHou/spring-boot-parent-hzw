package com.hzw.learn.springboot.javabase.lock;

import java.util.concurrent.locks.ReentrantLock;

/**
 * @ClassName FairLockTest
 * @Description TODO
 * @Author houzw
 * @Date 2022/12/1
 **/
public class FairLockTest {
        private ReentrantLock lock = new ReentrantLock(true);

        public void fairLock() {
            try {
                lock.lock();
                System.out.println(Thread.currentThread().getName()  + "正在持有锁");
                Thread.sleep(1000);
            } catch (InterruptedException e) {}
            finally {
                System.out.println(Thread.currentThread().getName()  + "释放了锁");
                lock.unlock();
            }
        }

        public static void main(String[] args) {
            FairLockTest myFairLock = new FairLockTest();
            Runnable runnable = () -> {
                System.out.println(Thread.currentThread().getName() + "启动");
                myFairLock.fairLock();
            };
            Thread[] thread = new Thread[10];
            for(int i = 0;i < 10;i++){
                thread[i] = new Thread(runnable);
            }
            for(int i = 0;i < 10;i++){
                thread[i].start();
            }
        }
    }
