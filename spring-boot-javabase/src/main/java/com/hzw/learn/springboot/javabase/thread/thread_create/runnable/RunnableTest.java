package com.hzw.learn.springboot.javabase.thread.thread_create.runnable;


public class RunnableTest implements Runnable{
	private int index = 0;
	@Override
	public void run() {
		for (; index < 50; index++) {
			System.out.println(Thread.currentThread().getName() + ":" + index);
		}
	}
	
	public static void main(String[] args) {
		// 实现runnable接口的线程实现
		new Thread(new RunnableTest()).start();
		
	}

}
