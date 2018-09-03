package com.hzw.learn.springboot.javabase.thread.callable;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

public class CallableImplements implements Callable<Integer>{

	@Override
	public Integer call() throws Exception {
		int i=0;
		for (;i<50;i++) {
			System.out.println(Thread.currentThread().getName() + ":" + i);
		}
		return i;
	}
	
	public static void main(String[] args) {
		FutureTask<Integer> task = new FutureTask<Integer>(new CallableImplements());
		new Thread(task, "有返回值的线程").start();

		try {
			System.out.println("子线程的返回值：" + task.get());
		} catch (InterruptedException e) {
			e.printStackTrace();
		} catch (ExecutionException e) {
			e.printStackTrace();
		}

	}
	
}