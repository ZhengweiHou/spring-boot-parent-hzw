package com.hzw.learn.springboot.jvm.线程安全.重入锁_ReentranLock;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ReentrantLock绑定多Condition {
	public static void main(String[] args) throws InterruptedException {
		Logger log = LoggerFactory.getLogger("MAIN");
		ReentrantLockWithCondition lockCondition = new ReentrantLockWithCondition();
		Thread t1 = new Thread(lockCondition,"线程1");
		t1.start();
		Thread.sleep(1000l);
		log.info("准备加锁");
		ReentrantLockWithCondition.lock.lock();
		log.info("加锁，计数器：{}",ReentrantLockWithCondition.lock.getHoldCount());
		Thread.sleep(1000l);
		log.info("condition标志等待结束");
		ReentrantLockWithCondition.condition.signal();
		Thread.sleep(1000l);
		log.info("释放锁");
		ReentrantLockWithCondition.lock.unlock();
	}
}

class ReentrantLockWithCondition implements Runnable {
	Logger log = LoggerFactory.getLogger("TEST");
	
	public static ReentrantLock lock = new ReentrantLock();
	public static Condition condition = lock.newCondition();
	
	@Override
	public void run() {
//		lock.newCondition();
		try {
			log.info("准备加锁");
			lock.lock();
			log.info("加锁，计数器：{}",lock.getHoldCount());
			Thread.sleep(3000l);
			log.info("condition等待,允许其他线程获取锁。。。");
			condition.await();
			log.info("condition等待结束。。。");
		} catch (InterruptedException e) {
			log.error("异常");
		}finally {
			if(lock.isHeldByCurrentThread()) {
				log.info("释放锁");
				lock.unlock();
			}
		}
	}
}