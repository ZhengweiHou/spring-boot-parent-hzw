package com.hzw.learn.springboot.jvm.线程安全;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * Atomicxxxx 原子变量
 * @author houzw
 *
 */
public class 使用CAS指令封装的非阻塞同步 {
	public static void main(String[] args) throws InstantiationException, IllegalAccessException, ClassNotFoundException {
		int COUNT = 20;
//		Thread[] threads_A = new Thread[COUNT];
//		Thread[] threads_O = new Thread[COUNT];
		for(int i = 0 ; i < COUNT ; i++) {
//			threads_A[i] = new Thread(new AutoicTest());
//			threads_O[i] = new Thread(new OrdinaryTest());
//			threads_A[i].start();
//			threads_O[i].start();

			new Thread(new AutoicTest()).start();
			new Thread(new OrdinaryTest()).start();
		}
		System.out.println("A_race = " + AutoicTest.race);
		System.out.println("O_race = " + OrdinaryTest.race);
	}
	/*
	测试结果
	A_race = 200000
	O_race = 195109
	*/
}
class AutoicTest implements Runnable{
	public static AtomicInteger race = new AtomicInteger(0);

	@Override
	public void run() {
		for(int i = 0; i < 10000 ; i++) {
			race.incrementAndGet();	// 类似于++race
		}
	}
}
class OrdinaryTest implements Runnable{
	public static int race = 0;
	
	@Override
	public void run() {
		for(int i = 0; i < 10000 ; i++) {
			++race;
		}
	}
}