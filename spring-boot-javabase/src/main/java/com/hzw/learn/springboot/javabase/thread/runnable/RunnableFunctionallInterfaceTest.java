package com.hzw.learn.springboot.javabase.thread.runnable;

public class RunnableFunctionallInterfaceTest {
	public static void main(String[] args) {
		
		// 使用java8函数式接口和Lambda表达式实现线程方法实现
		new Thread((Runnable)()->{
			for(int i=0;i<10;i++){
				System.out.println(Thread.currentThread().getName()+":"+i);
			}
		}).start();
	}
	
}
