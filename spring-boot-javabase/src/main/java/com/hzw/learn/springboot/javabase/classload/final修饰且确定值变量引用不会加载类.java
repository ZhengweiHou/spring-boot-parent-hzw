package com.hzw.learn.springboot.javabase.classload;

/**
 * @author houzw
 *
 *         运行结果： 
 *         aaa 
 *         静态初始化块 
 *         bbb 
 *         1543549242091ccc
 */
public class final修饰且确定值变量引用不会加载类 {
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
	static {
		System.out.println("静态初始化块");
	}

	static final String a = "aaa";	// final修饰且编译时能确定的变量其被引用处会直接被替换成常量 

	static String b = "bbb";

	static final String c = System.currentTimeMillis() + "ccc";
}
