package com.hzw.learn.springboot.jvm.内存模型;

public class synchronized有序性保障 {
	static Integer b = 0;
	public static void main(String[] args) {
		new Thread(() -> {
			for (int i = 0; i < 100; i++) {
				synchronized(b) {
					System.out.print("111>");
					b = 100;
					try {Thread.sleep(1l);} catch (Exception e) {}
					System.out.println("<111");
				}
				Thread.yield();
			}
		}).start()  ;
		
		new Thread(() -> {
			for (int i = 0; i < 100; i++) {
				synchronized(b) {
					b = 200;
					try {Thread.sleep(1l);} catch (Exception e) {}
					System.out.println("222=" + b);
				}
				Thread.yield();
			}
		}).start();  ;
	}
}
