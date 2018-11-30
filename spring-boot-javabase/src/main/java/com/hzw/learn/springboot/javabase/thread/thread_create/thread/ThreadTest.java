package com.hzw.learn.springboot.javabase.thread.thread_create.thread;

public class ThreadTest extends Thread {
	private int index = 0;

	@Override
	public void run() {
		for (; index < 50; index++) {
			System.out.println(getName() + ":" + index);
		}
	}

	public static void main(String[] args) {
		// 继承Thread类的线程实现
		new ThreadTest().start();

	}

}
