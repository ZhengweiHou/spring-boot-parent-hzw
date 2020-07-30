package com.hzw.learn.springboot.dubbo.serviceExportAndReferenceAndInvoke.api;

public interface Hi
{
	public String sayhi(String name) ;
	
	public String async_sayhi(String name);
}
