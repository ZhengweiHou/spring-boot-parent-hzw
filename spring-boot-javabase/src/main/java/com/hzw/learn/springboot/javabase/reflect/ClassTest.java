package com.hzw.learn.springboot.javabase.reflect;

import java.lang.annotation.Repeatable;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.ArrayList;
import java.util.List;

// 注解
@Deprecated
@Hzw
@Hzw
public class ClassTest 
extends ArrayList<String>  // 父类
implements List<String>    // 接口
{
	// 属性
	private   String privateStr;
	protected String protectedStr;
	public    String publicStr;
	
	// 构造器
	private ClassTest() {}
	public ClassTest(String str) {}
	
	// 方法
	private   String privateMethod()   {return "this is privateMethod";}
	protected String protectedMethod() {return "this is protectedMethod";}
	public    String publicMethod()    {return "this is publicMethod";}
	
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