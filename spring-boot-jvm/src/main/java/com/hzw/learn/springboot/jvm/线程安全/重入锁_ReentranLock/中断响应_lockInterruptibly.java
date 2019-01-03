package com.hzw.learn.springboot.jvm.线程安全.重入锁_ReentranLock;

import java.util.concurrent.locks.ReentrantLock;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class 中断响应_lockInterruptibly {
	public static void main(String[] args) throws InterruptedException {
		DeadLock deadLock1 = new DeadLock(1);
		DeadLock deadLock2 = new DeadLock(2);
		Thread t1 = new Thread(deadLock1, "线程1");
		Thread t2 = new Thread(deadLock2, "线程2");
		t1.start();
		t2.start();
		Thread.sleep(1000l);
		t1.interrupt(); // t1线程标记为中断状态
	}
}

/**
 * 可中断锁测试类
 * @author houzw
 */
class DeadLock implements Runnable {
	Logger log = LoggerFactory.getLogger("可中断锁");
	
	public static ReentrantLock lock1 = new ReentrantLock();
	public static ReentrantLock lock2 = new ReentrantLock();
	int locked;

	public DeadLock(int locked) {
		this.locked = locked;
	}

	@Override
	public void run() {
		try {
			if (locked == 1) {
				// 可响应中断方式加锁
				lock1.lockInterruptibly();
				log.info("获得lock1");
				Thread.sleep(500l);
				lock2.lockInterruptibly();
				log.info("获得lock2");
			}else {
				lock2.lockInterruptibly();
				log.info("获得lock2");
				Thread.sleep(500l);
				lock1.lockInterruptibly();
				log.info("获得lock1");
			}
		} catch (InterruptedException e) {
			log.info("中断");
		}finally {
			// 判断是否当前线程占用锁
			if(lock1.isHeldByCurrentThread()) {
				lock1.unlock();
				log.info("解锁lock1");
			}
			if(lock2.isHeldByCurrentThread()) {
				lock2.unlock();
				log.info("解锁lock2");
			}
			log.info("结束");
		}
	}

}
