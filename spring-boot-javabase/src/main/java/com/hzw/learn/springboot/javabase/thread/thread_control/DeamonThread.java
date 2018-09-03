package com.hzw.learn.springboot.javabase.thread.thread_control;

/**
 * 后台/守护/精灵线程
 * @author houzw
 *
 */
public class DeamonThread extends Thread{

	public DeamonThread(String name) {
		super(name);
	}

	@Override
	public void run() {
		for(int i=0 ; i<10000 ; i++){
			// 线程正常执行需要循环10000次
			System.out.println(getName() + ":" + i);
		}
	}
	
	public static void main(String[] args) {
		DeamonThread dt = new DeamonThread("守护线程");
		dt.setDaemon(true); // 设置dt线程为守护线程
		dt.start();
//		dt.setDaemon(true); // 线程启动后再设置为守护状态则不生效
		
		// 可以设置多个守护进程，守护进程之间没有守护依赖关系，main线程结束后，都会随之结束
//		DeamonThread dt2 = new DeamonThread("守护线程2");
//		dt2.setDaemon(true); // 设置dt线程为守护线程
//		dt2.start();

		
		for (int i = 0; i < 10; i++) {
			System.out.println(Thread.currentThread().getName() + ":" + i);
		}
		
		// main线程执行到此处就结束了，此时也没有其他“前台”线程正在执行，后台线程dt应随之结束，执行不了10000次循环
	}
}

/*
main:0
main:1
main:2
main:3
main:4
main:5
main:6
main:7
main:8
守护线程:0
main:9
守护线程:1
守护线程:2
守护线程:3
守护线程:4
守护线程:5
守护线程:6
守护线程:7
守护线程:8
守护线程:9
守护线程:10
守护线程:11
守护线程:12
*/