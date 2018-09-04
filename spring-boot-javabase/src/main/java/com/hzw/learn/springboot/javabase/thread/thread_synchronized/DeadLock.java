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
		// 获取并保持A对象的锁
		a.doSomething(b);
		System.out.println("主线程调用A实例的doSomething()方法结束");
	}
	
	@Override
	public void run() {
		
		// 获取并保持B对象的锁
		b.doSomething(a);
		System.out.println("子线程调用B实例的doSomething()方法结束");
	}
	
	public static void main(String[] args) {
		DeadLock test = new DeadLock();
		test.setName("子线程");
		test.start(); // 启动子线程
		
		Thread.currentThread().setName("主线程");
		test.mainDo(); // 主线程调用mainDao()方法
	}
	
}


class A extends X{
	public synchronized void doSomething(B b){
		System.out.println("线程[" + Thread.currentThread().getName() + "]在【A】实例的doSomething()方法里准备调用【B】实例的show()方法");
		// 调用方法前需要获取b对象的监视锁
		b.show();
	}
	
}

class B extends X{
	public synchronized void doSomething(A a) {
		System.out.println("线程[" + Thread.currentThread().getName() + "]在【B】实例的doSomething()方法里准备调用【A】实例的show()方法");
		// 调用方法前需要获取a对象的监视锁
		a.show();
	}
	
}

abstract class X{
	public synchronized void show(){
		System.out.println(this.getClass().getSimpleName() + "的show方法执行体");
	}
}

