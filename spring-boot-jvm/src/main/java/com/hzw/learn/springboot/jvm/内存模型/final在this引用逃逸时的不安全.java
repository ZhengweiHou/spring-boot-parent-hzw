package com.hzw.learn.springboot.jvm.内存模型;

public class final在this引用逃逸时的不安全 {
	public static void main(String[] args) {
		new A();
	}
}
class A{
	public final int a;
	public A() {
		new B(this);
		try {
			Thread.sleep(1l);
		} catch (InterruptedException e) {}
		a = 222;
	}
}
class B{
	public B(A a) {
		new Thread(() -> {
			System.out.println(a.a);
			try {
				Thread.sleep(50l);
			} catch (InterruptedException e) {}
			System.out.println(a.a);
		}).start();
	}
}
