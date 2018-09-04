package com.hzw.learn.springboot.javabase.thread.thread_synchronized;

/**
 * 死锁测试
 * @author houzw
 *
 */
public class DeadLock extends Thread{
	private A a = new A();
	private B b = new B();
	
	public void mainDo(){
		Thread.currentThread().setName("主线程");
		a.doSomething(b);
		System.out.println("主线程调用了A实例的doSomething()方法");
	}
	
	@Override
	public void run() {
		Thread.currentThread().setName("子线程");
		b.doSomething(a);
		System.out.println("子线程调用了B实例的doSomething()方法");
	}
	
	public static void main(String[] args) {
		DeadLock test = new DeadLock();
		test.start(); // 启动子线程
		test.mainDo(); // 主线程调用mainDao()方法
	}
	
}


class A extends X{
	public synchronized void doSomething(B b){
		System.out.println("线程[" + Thread.currentThread().getName() + "]在【A】实例的doSomething()方法里");
//		try {
//			Thread.sleep(1000);
//		} catch (InterruptedException e) {
//			e.printStackTrace();
//		}
		System.out.println("线程[" + Thread.currentThread().getName() + "]在【A】实例的doSomething()方法里准备调用【B】实例的show()方法");
		b.show();
	}
	
}

class B extends X{
	public synchronized void doSomething(A a) {
		System.out.println("线程[" + Thread.currentThread().getName() + "]在【B】实例的doSomething()方法里");
//		try {
//			Thread.sleep(1000);
//		} catch (InterruptedException e) {
//			e.printStackTrace();
//		}
		System.out.println("线程[" + Thread.currentThread().getName() + "]在【B】实例的doSomething()方法里准备调用【A】实例的show()方法");
		a.show();
	}
	
}

abstract class X{
	public synchronized void show(){
		System.out.println(this.getClass().getSimpleName() + "的show方法执行体");
	}
}

