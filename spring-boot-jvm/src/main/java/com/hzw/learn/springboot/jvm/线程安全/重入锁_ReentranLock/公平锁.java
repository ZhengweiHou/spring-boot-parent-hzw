package com.hzw.learn.springboot.jvm.线程安全.重入锁_ReentranLock;

import java.util.concurrent.locks.ReentrantLock;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class 公平锁 {
	public static void main(String[] args) throws InterruptedException {
		FairLock fairLock = new FairLock();
		for(int i=1 ; i <=5 ; i++) {
			new Thread(fairLock, "线程"+i).start();
			Thread.sleep(20l);
		}
	}
}
/*结果：
18:23:21.353 [线程1] INFO 公平锁 - 线程运行...
18:23:21.356 [线程1] INFO 公平锁 - 获取锁
18:23:21.371 [线程2] INFO 公平锁 - 线程运行...
18:23:21.391 [线程3] INFO 公平锁 - 线程运行...
18:23:21.411 [线程4] INFO 公平锁 - 线程运行...
18:23:21.432 [线程5] INFO 公平锁 - 线程运行...
18:23:22.356 [线程1] INFO 公平锁 - 解锁
18:23:22.356 [线程2] INFO 公平锁 - 获取锁
18:23:23.357 [线程2] INFO 公平锁 - 解锁
18:23:23.357 [线程3] INFO 公平锁 - 获取锁
18:23:24.357 [线程3] INFO 公平锁 - 解锁
18:23:24.357 [线程4] INFO 公平锁 - 获取锁
18:23:25.358 [线程4] INFO 公平锁 - 解锁
18:23:25.358 [线程5] INFO 公平锁 - 获取锁
18:23:26.358 [线程5] INFO 公平锁 - 解锁
*/

class FairLock implements Runnable {
	Logger log = LoggerFactory.getLogger("公平锁");

	// 构造器传入true，以实例出公平锁
	public static ReentrantLock lock = new ReentrantLock(true);

	@Override
	public void run() {
		log.info("线程运行...");
		while (true) {
			try {
				lock.lock();
				log.info("获取锁");
				Thread.sleep(1000l);
			} catch (InterruptedException e) {;
			}finally {
				log.info("解锁");
				lock.unlock();
			}
		}
	}

}