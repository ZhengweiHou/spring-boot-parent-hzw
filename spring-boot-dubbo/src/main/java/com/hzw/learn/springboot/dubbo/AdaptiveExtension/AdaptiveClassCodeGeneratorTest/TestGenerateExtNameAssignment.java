package com.hzw.learn.springboot.dubbo.AdaptiveExtension.AdaptiveClassCodeGeneratorTest;

import com.google.gson.Gson;
import org.junit.Test;

/**
 * AdaptiveClassCodeGenerator.generateExtNameAssignment() 方法，
 * 扩展对象名称解析代码生成器过程太过复杂，该测试用于辅助理解代码生成逻辑
 */
public class TestGenerateExtNameAssignment {

	public static final String CODE_EXT_NAME_ASSIGNMENT = "String extName = %s;";

	public static String defaultExtName = "hzw";

	public static String newm(String[] value, boolean hasInvocation){
		String getNameCode = null;
		for (int i = value.length - 1; i >= 0; --i) {
			boolean isLastOne = (i == value.length - 1);
			if ("protocol".equals(value[i])) {
				if (isLastOne){
					if (null != defaultExtName){
					getNameCode = String.format("( url.getProtocol() == null ? \"%s\" : url.getProtocol() )", defaultExtName);
					}else{
						getNameCode = "url.getProtocol()";
					}
				}else{
					getNameCode = String.format("url.getProtocol() == null ? (%s) : url.getProtocol()", getNameCode);
				}
			}else{
				if (hasInvocation){
					getNameCode = String.format("url.getMethodParameter(methodName, \"%s\", \"%s\")", value[i], defaultExtName);
				}else{
					if (isLastOne){
						if (null != defaultExtName) {
							getNameCode = String.format("url.getParameter(\"%s\", \"%s\")", value[i], defaultExtName);
						}else{
							getNameCode = String.format("url.getParameter(\"%s\")", value[i]);
						}
					}else{
						getNameCode = String.format("url.getParameter(\"%s\", %s)", value[i], getNameCode);
					}
				}
			}
		}

		return String.format(CODE_EXT_NAME_ASSIGNMENT, getNameCode);
	}

	public static String oldm(String[] value, boolean hasInvocation) {
		// TODO: refactor it
		String getNameCode = null;
		for (int i = value.length - 1; i >= 0; --i) {
			if (i == value.length - 1) {
				// 数组最后一个，第一个执行
				if (null != defaultExtName) {
					if (!"protocol".equals(value[i])) {
						if (hasInvocation) {
							// url.getMethodParameter(String method, String key, String defaultValue)
							getNameCode = String.format("url.getMethodParameter(methodName, \"%s\", \"%s\")", value[i], defaultExtName);
						} else {
							// url.getParameter(String key, String defaultValue)
							getNameCode = String.format("url.getParameter(\"%s\", \"%s\")", value[i], defaultExtName);
						}
					} else {
						// ( url.getProtocol() == null ? defaultExtName : url.getProtocol() )
						getNameCode = String.format("( url.getProtocol() == null ? \"%s\" : url.getProtocol() )", defaultExtName);
					}
				} else {
					if (!"protocol".equals(value[i])) {
						if (hasInvocation) {
							// url.getMethodParameter(String method, String key, null)
							getNameCode = String.format("url.getMethodParameter(methodName, \"%s\", \"%s\")", value[i], defaultExtName);
						} else {
							// url.getParameter(String key)
							getNameCode = String.format("url.getParameter(\"%s\")", value[i]);
						}
					} else {
						// url.getProtocol()
						getNameCode = "url.getProtocol()";
					}
				}
			} else {
				if (!"protocol".equals(value[i])) {
					if (hasInvocation) {
						// url.getMethodParameter(String method, String key, String defaultValue)
						getNameCode = String.format("url.getMethodParameter(methodName, \"%s\", \"%s\")", value[i], defaultExtName);
					} else {
						// url.getParameter(String key, String defaultValue)
						getNameCode = String.format("url.getParameter(\"%s\", %s)", value[i], getNameCode);
					}
				} else {
					// ( url.getProtocol() == null ? (..getNameCode..) : url.getProtocol() )
					getNameCode = String.format("url.getProtocol() == null ? (%s) : url.getProtocol()", getNameCode);
				}
			}
		}

		return String.format(CODE_EXT_NAME_ASSIGNMENT, getNameCode);
	}




	public static String generateExtNameAssignment(String[] value, boolean hasInvocation) {
//		return oldm(value,hasInvocation);
		return newm(value,hasInvocation);
	}

	@Test
	public static void test(String[] args) {

//		Test.defaultExtName=null;
		String[] value = {"hhh","zzz","www"};
		System.out.println("===============================" + new Gson().toJson(value));
		System.out.println(TestGenerateExtNameAssignment.generateExtNameAssignment(value,true));
		System.out.println(TestGenerateExtNameAssignment.generateExtNameAssignment(value,false));

		String[] value1 = {"hhh","zzz","www","protocol"};
		System.out.println("===============================" + new Gson().toJson(value1));
		System.out.println(TestGenerateExtNameAssignment.generateExtNameAssignment(value1,true));
		System.out.println(TestGenerateExtNameAssignment.generateExtNameAssignment(value1,false));

		String[] value2 = {"protocol","hhh","zzz","www"};
		System.out.println("===============================" + new Gson().toJson(value2));
		System.out.println(TestGenerateExtNameAssignment.generateExtNameAssignment(value2,true));
		System.out.println(TestGenerateExtNameAssignment.generateExtNameAssignment(value2,false));

		String[] value5 = {"hhh","protocol","www"};
		System.out.println("===============================" + new Gson().toJson(value5));
		System.out.println(TestGenerateExtNameAssignment.generateExtNameAssignment(value5,true));
		System.out.println(TestGenerateExtNameAssignment.generateExtNameAssignment(value5,false));

		String[] value3 = {"protocol","hhh","zzz","www","protocol"};
		System.out.println("===============================" + new Gson().toJson(value3));
		System.out.println(TestGenerateExtNameAssignment.generateExtNameAssignment(value3,true));
		System.out.println(TestGenerateExtNameAssignment.generateExtNameAssignment(value3,false));

		String[] value4 = {"protocol","hhh","protocol","www","protocol"};
		System.out.println("===============================" + new Gson().toJson(value4));
		System.out.println(TestGenerateExtNameAssignment.generateExtNameAssignment(value4,true));
		System.out.println(TestGenerateExtNameAssignment.generateExtNameAssignment(value4,false));



	}
}
