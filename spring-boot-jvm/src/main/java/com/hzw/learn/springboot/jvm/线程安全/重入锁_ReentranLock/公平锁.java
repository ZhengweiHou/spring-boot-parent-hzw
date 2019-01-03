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
18:39:12.844 [线程1] INFO 公平锁 - 线程运行...
18:39:12.846 [线程1] INFO 公平锁 - 获取锁,计数器:1
18:39:12.862 [线程2] INFO 公平锁 - 线程运行...
18:39:12.883 [线程3] INFO 公平锁 - 线程运行...
18:39:12.912 [线程4] INFO 公平锁 - 线程运行...
18:39:12.932 [线程5] INFO 公平锁 - 线程运行...
18:39:13.848 [线程1] INFO 公平锁 - 解锁
18:39:13.849 [线程2] INFO 公平锁 - 获取锁,计数器:1
18:39:14.849 [线程2] INFO 公平锁 - 解锁
18:39:14.850 [线程3] INFO 公平锁 - 获取锁,计数器:1
18:39:15.850 [线程3] INFO 公平锁 - 解锁
18:39:15.850 [线程4] INFO 公平锁 - 获取锁,计数器:1
18:39:16.850 [线程4] INFO 公平锁 - 解锁
18:39:16.851 [线程5] INFO 公平锁 - 获取锁,计数器:1
18:39:17.851 [线程5] INFO 公平锁 - 解锁
18:39:17.851 [线程1] INFO 公平锁 - 获取锁,计数器:1
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
//				lock.lock(); // 拥有锁的线程，多次加锁，可重入，计数器累加
				log.info("获取锁,计数器:{}",lock.getHoldCount());
				Thread.sleep(1000l);
			} catch (InterruptedException e) {;
			}finally {
				log.info("解锁");
				lock.unlock();
//				lock.unlock(); // 多次加锁，需要多次解锁，将计数器降为0以解锁
			}
		}
	}

}