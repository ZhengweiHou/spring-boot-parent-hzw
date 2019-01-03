package com.hzw.learn.springboot.jvm.线程安全.重入锁_ReentranLock;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class 锁等待限时_tryLock {
	public static void main(String[] args) {
		TryLocks t1 = new TryLocks(); t1.setName("线程1");
		TryLocks t2 = new TryLocks(); t2.setName("线程2");
		t1.start();
		t2.start();
	}
/*
结果：
17:58:15.988 [线程2] INFO 锁等待限时 - 尝试获取锁
17:58:15.988 [线程1] INFO 锁等待限时 - 尝试获取锁
17:58:15.992 [线程1] INFO 锁等待限时 - 获取并占用锁
17:58:16.992 [线程2] INFO 锁等待限时 - 获取锁失败！
17:58:17.992 [线程1] INFO 锁等待限时 - 解锁
*/
	
}

class TryLocks extends Thread {
	Logger log = LoggerFactory.getLogger("锁等待限时");

	public static ReentrantLock lock = new ReentrantLock();

	@Override
	public void run() {
		try {
			log.info("尝试获取锁");
			if (lock.tryLock(1, TimeUnit.SECONDS)) {
				log.info("获取并占用锁");
				Thread.sleep(2000l);
			}else {
				log.info("获取锁失败！");
			}
		} catch (InterruptedException e) {
			log.info("线程异常");
		}finally {
			if(lock.isHeldByCurrentThread()) {
				 lock.unlock();
				 log.info("解锁");
			}
		}
	}
}