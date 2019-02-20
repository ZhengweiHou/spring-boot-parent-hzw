package com.hzw.learn.springboot.jvm.内存模型;

public class volatile变量非线程安全 {
	public static volatile int race = 0;
	
	public static void increase() {
		race ++;
	}
	
	// 如果是正常并发的话，结果应该输出200000
	public static void main(String[] args) {
		for (int i = 0 ; i < 20 ; i++) {
			new Thread(() -> {
				for (int n = 0 ;  n < 10000 ; n++) {
					increase();
				}
			}).start();
		}
		
		while (Thread.activeCount() > 1) {
			Thread.yield();
		}
		
		System.out.println(race);
	}
}
