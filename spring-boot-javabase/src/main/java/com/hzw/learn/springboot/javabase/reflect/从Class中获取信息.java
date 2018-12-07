package com.hzw.learn.springboot.javabase.reflect;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.lang.reflect.Parameter;
import java.util.Arrays;

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
		
		// 方法对象-抽象基类：Executable（Constructor同Method，都是Executable的子类）
		Constructor<ClassTest> cons = clazz.getConstructor(String.class);
		int pCount = cons.getParameterCount();	// 方法参数个数
		Parameter[] ps = cons.getParameters();		// 方法形参数组
		
		// 参数对象
		Parameter parameter = ps[0];
		parameter.getModifiers();
		parameter.getName();
		parameter.getParameterizedType();
		parameter.getType();
		parameter.isNamePresent();
		parameter.isVarArgs();
		
	}
}
