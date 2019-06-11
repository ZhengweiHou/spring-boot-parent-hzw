package com.hzw.learn.springboot.springbase.springbean;

import org.springframework.beans.factory.InitializingBean;

public class TestBean implements InitializingBean{

	public Field1 field1;
	public Field2 field2;
	
	private String consarg;
	
	public TestBean(String constr1, String constr2, Cons1 cons1, Cons2 cons2) {
		consarg = "constr1=" + constr1 + " constr2=" + constr2 + " cons1=" + cons1 + " cons2=" + cons2;
//		System.out.println("constr1=" + constr1 + " constr2=" + constr2 + " cons1=" + cons1 + " cons2=" + cons2);
	}

	@Override
	public void afterPropertiesSet() throws Exception {
		System.out.println(consarg);
		System.out.println(field1+","+field2);
		
	}
}
