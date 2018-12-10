package com.hzw.learn.springboot.javabase.reflect;

import java.lang.annotation.Repeatable;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.List;

// 注解
@Deprecated
@Hzw
@Hzw
public class ClassTest 
extends ParentClassTest  // 父类
implements InterfaceTest
{
	// 属性
	private   String privateStr;
	protected String protectedStr;
	public    String publicStr;
	
	// 构造器

	public ClassTest() {System.out.println("无参构造器输出！");}
	private ClassTest(Integer inte) {}
	public ClassTest(String str) {System.out.println("构造器输出：" + str);}
	
	public ClassTest(String strName, Integer intName, List<String> list) {}
	
	// 方法
	private   String privateMethod()   {return "this is privateMethod";}
	protected String protectedMethod() {return "this is protectedMethod";}
	public    String publicMethod(String str)    {return "this is publicMethod";}
	
	// 内部类
	class InnerClass{}

}

@Repeatable(Hzws.class) // Java8新增的，定义重复注解
@interface Hzw {
}

@Retention(RetentionPolicy.RUNTIME)
@interface Hzws {
	Hzw[] value();
}

abstract class ParentClassTest{
	private   String par_privateStr;
	protected String par_protectedStr;
	public    String par_publicStr;
	
	// 方法
	private   String par_privateMethod()   {return "this is parprivateMethod";}
	protected String par_protectedMethod() {return "this is parprotectedMethod";}
	public    String par_publicMethod()    {return "this is parpublicMethod";}
	
}

interface InterfaceTest{
	public String interfaceStr = "";
	public default void interfaceMathod() {};
}
