package com.hzw.learn.springboot.javabase.thread.thread_create.callable;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

public class CallableImplements implements Callable<Integer> {

	@Override
	public Integer call() throws Exception {
		int i = 0;
		for (; i < 50; i++) {
			System.out.println(Thread.currentThread().getName() + ":" + i);
		}
		Thread.sleep(2000l);
		return i;
	}

	public static void main(String[] args) {
		FutureTask<Integer> task = new FutureTask<Integer>(new CallableImplements());
		new Thread(task, "有返回值的线程").start();

		try {
			System.out.println("子线程的返回值：" + task.get()); // task.get()会阻塞等待子线程返回数据
		} catch (InterruptedException e) {
			e.printStackTrace();
		} catch (ExecutionException e) {
			e.printStackTrace();
		}

	}

}