package com.hzw.learn.springboot.javabase.thread.thread_control;

/**
 * 线程等待
 * 
 * @author houzw
 *
 */
public class JoinThread extends Thread {
	public JoinThread(String name) {
		super(name);
	}

	@Override
	public void run() {
		for (int i = 0; i < 10; i++) {
			System.out.println(getName() + ":" + i);
			if (i == 5) {
				try {
					this.sleep(3 * 1000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
	}

	public static void main(String[] args) throws InterruptedException {
		new JoinThread("主线程启动的子线程").start();

		for (int i = 0; i < 10; i++) {
			if (i == 5) {
				JoinThread joinThread = new JoinThread("被主线程join的线程");
				joinThread.start();
				joinThread.join(); // 当前线程等待joinThread线程执行完才会继续执行
			}

			System.out.println(Thread.currentThread().getName() + ":" + i);
		}
	}

}
