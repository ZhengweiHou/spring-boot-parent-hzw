package com.hzw.learn.springboot.javabase;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.hzw.learn.springboot.javabase.thread.thread_create.thread.ThreadTest;

@Component
public class RunStart implements CommandLineRunner{

	@Override
	public void run(String... arg0) throws Exception {
		System.out.println("run start ...");
	}

}
