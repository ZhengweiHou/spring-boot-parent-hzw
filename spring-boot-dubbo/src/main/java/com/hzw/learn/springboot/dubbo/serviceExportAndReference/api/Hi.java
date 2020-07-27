package com.hzw.learn.springboot.dubbo.serviceExportAndReference.api;

public interface Hi
{
	public String sayhi(String name) ;
	
	public String async_sayhi(String name);
}
