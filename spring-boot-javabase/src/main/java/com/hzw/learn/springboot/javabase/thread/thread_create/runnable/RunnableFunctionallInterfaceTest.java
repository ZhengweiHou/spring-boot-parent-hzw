package com.hzw.learn.springboot.javabase.thread.thread_create.runnable;

/**
 * Runnable函数式接口
 * @author houzw
 *
 */
public class RunnableFunctionallInterfaceTest {
	public static void main(String[] args) {
		// 使用java8函数式接口和Lambda表达式实现线程方法实现
		
//		1
		Runnable a = () -> {System.out.println(Thread.currentThread().getName());};
		
		Object b = (Runnable)() -> {System.out.println(Thread.currentThread().getName());};
		
		new Thread(() -> {
			for(int i=0;i<10;i++){
				System.out.println(Thread.currentThread().getName()+":"+i);
			}
		});
		
		
	}
	
}


