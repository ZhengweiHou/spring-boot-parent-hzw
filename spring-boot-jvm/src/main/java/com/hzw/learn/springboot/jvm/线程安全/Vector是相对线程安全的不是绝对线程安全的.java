package com.hzw.learn.springboot.jvm.线程安全;

import java.util.Vector;
import java.util.concurrent.locks.ReentrantLock;

public class Vector是相对线程安全的不是绝对线程安全的 {

	private static Vector<Integer> vector = new Vector<Integer>();
	public static void main(String[] args) {
		for (int n = 0; n < 100; n++) {
			for (int i = 0; i < 10; i++) {
				vector.add(i);
			}
			Thread removeThread = new Thread(new Runnable() {
				public void run() {
//					synchronized (vector) { // 不加同步块时，该案例中vector使用，特殊时会产生异常
					for (int i = 0; i < vector.size(); i++) {
						vector.remove(i);
					}
//					}
				}
			});
			Thread getThread = new Thread(new Runnable() {
				public void run() {
//					synchronized (vector) {
					for (int i = 0; i < vector.size(); i++) {
						vector.get(i);
					}
//					}
				}
			});
			removeThread.start();
			getThread.start();
			while (Thread.activeCount() > 20);
		}
	}
}
