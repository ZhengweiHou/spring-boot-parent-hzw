package com.hzw.learn.springboot.dubbo.hello.provider;

public interface Hi
{
	public String sayhi(String name) ;
	
	public String async_sayhi(String name);
}
