package com.hzw.learn.springboot.jvm.内存分配和回收;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class 对象优先在eden分配 {
	public static void main(String[] args) {
		testAllocation();
	}
	
	public static final int _1MB = 1024 * 1024;
	public static final int _1KB = 1024;
	
	/*对象优先在eden分配
	 * 
	 * JVM参数
	 * -Xloggc:testAllocation_GC.log -XX:+PrintGCDetails -XX:+PrintHeapAtGC 
	 * -Xms20M -Xmx20M -Xmn10M -XX:SurvivorRatio=8
	 *       堆大小     新生代大小    survivor:eden=1:8               
	 * 
	 * */
	public static void testAllocation() {
		Scanner scan = new Scanner(System.in);
		List<byte[]> list = new ArrayList<byte[]>();
		
		for(;;) {
			System.out.println("输入要创建空间大小(单位:M):");
			int size = scan.nextInt();
			list.add(new byte[size * _1MB]);
		}
		
//		list.add(new byte[2 * _1MB]);
//		list.add(new byte[2 * _1MB]);
//		list.add(new byte[2 * _1MB]);
//		list.add(new byte[4 * _1MB]);
	}
	
}
