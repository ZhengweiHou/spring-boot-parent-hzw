package com.hzw.learn.springboot.jvm.内存模型;

public class Test {
	public static volatile int race = 0;
	
	public static void increase() {
		race ++;
	}
	
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
