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
	 * -XX:+UseSerialGC -Xms20M -Xmx20M -Xmn10M -XX:SurvivorRatio=8
	 * 使用Serial/SerialOld收集器   堆大小  新生代大小    survivor:eden=1:8               
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
//		list.add(new byte[4 * _1MB]); // 出现一次Minor GC
	}
	/* GC日志：
	Java HotSpot(TM) 64-Bit Server VM (25.172-b11) for linux-amd64 JRE (1.8.0_172-b11), built on Mar 28 2018 21:44:09 by "java_re" with gcc 4.3.0 20080428 (Red Hat 4.3.0-8)
	Memory: 4k page, physical 16334944k(240444k free), swap 6291452k(6275484k free)
	CommandLine flags: -XX:InitialHeapSize=20971520 -XX:MaxHeapSize=20971520 -XX:MaxNewSize=10485760 -XX:NewSize=10485760 -XX:+PrintGC -XX:+PrintGCDateStamps -XX:+PrintGCDetails -XX:+PrintGCTimeStamps -XX:+PrintHeapAtGC -XX:SurvivorRatio=8 -XX:+UseCompressedClassPointers -XX:+UseCompressedOops -XX:+UseSerialGC 
	{Heap before GC invocations=0 (full 0):
	 def new generation   total 9216K, used 7968K [0x00000000fec00000, 0x00000000ff600000, 0x00000000ff600000)
	  eden space 8192K,  97% used [0x00000000fec00000, 0x00000000ff3c8128, 0x00000000ff400000)
	  from space 1024K,   0% used [0x00000000ff400000, 0x00000000ff400000, 0x00000000ff500000)
	  to   space 1024K,   0% used [0x00000000ff500000, 0x00000000ff500000, 0x00000000ff600000)
	 tenured generation   total 10240K, used 0K [0x00000000ff600000, 0x0000000100000000, 0x0000000100000000)
	   the space 10240K,   0% used [0x00000000ff600000, 0x00000000ff600000, 0x00000000ff600200, 0x0000000100000000)
	 Metaspace       used 3748K, capacity 4600K, committed 4864K, reserved 1056768K
	  class space    used 418K, capacity 428K, committed 512K, reserved 1048576K
	2019-04-26T18:00:11.974+0800: 51.793: [GC (Allocation Failure) 2019-04-26T18:00:11.974+0800: 51.793: [DefNew: 7968K->795K(9216K), 0.0070494 secs] 7968K->6939K(19456K), 0.0073291 secs] [Times: user=0.00 sys=0.00, real=0.01 secs] 
	*/
	
}
