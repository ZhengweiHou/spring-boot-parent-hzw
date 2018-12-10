package com.hzw.learn.springboot.javabase.reflect;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.lang.reflect.Parameter;
import java.util.Arrays;
import java.util.List;

public class 从Class中获取信息 {
	public static void main(String[] args) throws NoSuchMethodException, SecurityException {
		Class<ClassTest> clazz = ClassTest.class;
		
//		Field[] fs = clazz.getFields();
		Arrays.asList(clazz.getFields()					).forEach(temp->System.out.println("Fields               " + temp));
		Arrays.asList(clazz.getDeclaredFields()			).forEach(temp->System.out.println("DeclaredFields       " + temp));

//		Constructor<?>[] cs = clazz.getConstructors();
		Arrays.asList(clazz.getConstructors()			).forEach(temp->System.out.println("Constructors         " + temp));
		Arrays.asList(clazz.getDeclaredConstructors()	).forEach(temp->System.out.println("DeclaredConstructors " + temp));

//		Method[] ms = clazz.getMethods();
		Arrays.asList(clazz.getMethods()				).forEach(temp->System.out.println("Methods              " + temp));
		Arrays.asList(clazz.getDeclaredMethods()		).forEach(temp->System.out.println("DeclaredMethods      " + temp));
		// 此处getDeclaredMethods()能获取到ClassTest.par_publicMethod()方法，
		// javap反编译ClassTest.class和ParentClassTest.class发现，两二进制文件都有par_publicMethod()方法。
		// 但将ParentClassTest类的定义从ClassTest.java文件中提出来，就不会有这个问题。

		Arrays.asList(clazz.getAnnotations()			).forEach(temp->System.out.println("Annotations          " + temp));
		Arrays.asList(clazz.getDeclaredAnnotations()	).forEach(temp->System.out.println("DeclaredAnnotations  " + temp));
		
		Hzw annHzw = clazz.getAnnotation(Hzw.class);
		annHzw = clazz.getDeclaredAnnotation(Hzw.class);
		// 下面两个方法是，Java8提供的获取指定注解类的数组，对应重复注解功能提供的反射方法
		Hzw[] annoHzws = clazz.getAnnotationsByType(Hzw.class);
		annoHzws = clazz.getDeclaredAnnotationsByType(Hzw.class);
		
		Arrays.asList(clazz.getClasses()				).forEach(System.out::println);
		// 内部类
		Arrays.asList(clazz.getDeclaredClasses()		).forEach(System.out::println);
		// 外部类
		System.out.println(ClassTest.InnerClass.class.getDeclaringClass());
		// 接口
		Arrays.asList(clazz.getInterfaces()				).forEach(System.out::println);
		// 父类
		System.out.println(clazz.getSuperclass());
		
		// 类上所有修饰符，getModifiers()获取的是int，通过Modifier.toString(int mod)转换成文字描述
		int a = clazz.getModifiers();
		System.out.println(Modifier.toString(a));
		// 获取此类的包
		Package p = clazz.getPackage();
		System.out.println(p);
		
		System.out.println(clazz.getName());
		System.out.println(clazz.getSimpleName());
		
		///////////////////////////Executable/////////////////////////////////////
		// 方法对象-抽象基类：Executable（Constructor同Method，都是Executable的子类）
		Method method = clazz.getMethod("publicMethod",String.class);
		System.out.println(""
				+ "    方法名：" + method.getName()
				+ "    参数个数：" + method.getParameterCount()
		);
		Constructor<ClassTest> cons = clazz.getConstructor(String.class,Integer.class,List.class);
		System.out.println(""
				+ "    构造器名：" + cons.getName()
				+ "    参数个数：" + cons.getParameterCount()
		);
		// 方法形参数组
		Parameter[] ps = cons.getParameters();
		
		Arrays.asList(ps).stream().forEach(paramet -> {
			// 获取参数信息
			System.out.println(""
				+ "    " + paramet.getModifiers()
//				+ "    " + Modifier.toString(paramet.getModifiers())
				+ "    " + paramet.getName()
				+ "    " + paramet.getParameterizedType()
				+ "    " + paramet.getType()
				+ "    " + paramet.isNamePresent()
				+ "    " + paramet.isVarArgs()
			);
		});
		
//	    方法名：publicMethod    参数个数：1
//	    构造器名：com.hzw.learn.springboot.javabase.reflect.ClassTest    参数个数：3
//	    0    arg0    class java.lang.String    class java.lang.String    false    false
//	    0    arg1    class java.lang.Integer    class java.lang.Integer    false    false
//	    0    arg2    java.util.List<java.lang.String>    interface java.util.List    false    false
		
	}
}
