package com.hzw.learn.springboot.javabase.thread.thread_control;

public class YieldAndPriority extends Thread{

	public YieldAndPriority(String name) {
		super(name);
	}

	@Override
	public void run() {
		for(int i=0 ; i<20; i++){
			System.out.println(getName() + ":" + i + " priority:" + getPriority());
			if(i == 10){
				Thread.yield(); // 使用yield()方法使当前线程让步
			}
		}
	}
	
	/**
	 * 注意！！！ 单核处理器才能看出区别
	 * @param args
	 */
	public static void main(String[] args) {
		
		Thread.currentThread().setPriority(6);
		
		YieldAndPriority yt1 = new YieldAndPriority("MAX thread");
		yt1.setPriority(Thread.MAX_PRIORITY); // 设置优先级最高
		
		YieldAndPriority yt2 = new YieldAndPriority("MIN thread");
		yt2.setPriority(Thread.MIN_PRIORITY); // 设置优先级最低
		
		YieldAndPriority ytn = new YieldAndPriority("NORM thread");
		ytn.setPriority(Thread.NORM_PRIORITY); // 设置优先级为普通
		
		YieldAndPriority ytd = new YieldAndPriority("DEFAULT thread"); // 默认优先级，同父进程的优先级
		
		yt1.start();
		yt2.start();
		ytn.start();
		ytd.start();
	}
	
}
