package com.hzw.learn.springboot.javabase.lock;

import org.junit.Test;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * @ClassName ReadWriteLockTest
 * @Description 读写锁测试
 * @Author houzw
 * @Date 2022/12/2
 **/
/*
readLock和writeLock是互斥的；
readLock和readLock是可重入的；
writeLock和writeLock是互斥的；
 */
public class ReadWriteLockTest {
    private final ReadWriteLock lock = new ReentrantReadWriteLock();

    @Test
    public void test1(){

        new Thread(()->{
            while(true) {
                lock.readLock().lock();
                log("加锁");
                sleep(1000);
                log("解锁");
                lock.readLock().unlock();
            }
        },"r1").start();

        new Thread(()->{
            while(true) {
                lock.readLock().lock();
                log("加锁");
                sleep(1000);
                log("解锁");
                lock.readLock().unlock();
            }
        },"r2").start();

        new Thread(()->{
            while(true) {
                log("准备加锁");
                lock.writeLock().lock();
                log("加锁");
                sleep(1000);
                log("解锁");
                lock.writeLock().unlock();
                sleep(500);
            }
        },"w1").start();


        new Thread(()->{
            while(true) {
                log("准备加锁");
                lock.writeLock().lock();
                log("加锁");
                sleep(1000);
                log("解锁");
                lock.writeLock().unlock();
                sleep(500);
            }
        },"w2").start();

        sleep(10000);
    }


    public static void log(String msg){
        SimpleDateFormat dateFormat = new SimpleDateFormat( "HH:mm:ss:sss");
        System.out.println("" + dateFormat.format(new Date()) + " " + Thread.currentThread().getName()  + msg);
    }

    public static void sleep(long time){
        try {
            Thread.sleep(time);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
