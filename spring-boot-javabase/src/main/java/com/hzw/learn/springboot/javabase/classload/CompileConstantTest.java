package com.hzw.learn.springboot.javabase.classload;

/**
 * @author houzw
 *
运行结果：
aaa
静态初始化块
bbb
1543549242091ccc
 */
public class CompileConstantTest {
	public static void main(String[] args) {
		
		System.out.println(HZWTest.a); // 不会初始化HZWTest
		System.out.println(HZWTest.b); // 会初始化HZWTest
		System.out.println(HZWTest.c); // 会初始化HZWTest
	}

//	编译后的效果
//	  public static void main(String[] args)
//	  {
//	    System.out.println("aaa");
//	    System.out.println(HZWTest.b);
//	    System.out.println(HZWTest.c);
//	  }
}



class HZWTest {
	static
	{
		System.out.println("静态初始化块");
	}
	
	static final String a="aaa";
	
	static String b="bbb";
	
	static final String c=System.currentTimeMillis()+"ccc";
}
