package com.hzw.learn.springboot.jvm.EscapAnalysis;

/**
 * 逃逸分析-栈分配
 * @author houzw
 */
public class StackAllocation {
	public static void main(String[] args) throws Exception {
		int count = 2 * 100 * 100 * 100;
		int sum = 0;
		
		for(int i=0 ; i < count ; i++) {
			sum += test(i);
			System.out.println(sum);
		}
		
		Thread.sleep(50l);

		System.in.read();
	}
	
	public static int test(int age) {
		/*
		 User对象的作用域不会逃逸出test方法，当启用逃逸分析，标量替换优化会在栈上分配对象，而不会生成 User对象，减低GC的压力
		 */
		User usr = new User(age);
//		int age2 = usr.getAge();
		return usr.getAge();
	}
}

class User {
	private int age;
	
	public User(int age) {
		super();
		this.age = age;
	}

	public int getAge() {
		return age;
	}
	
}