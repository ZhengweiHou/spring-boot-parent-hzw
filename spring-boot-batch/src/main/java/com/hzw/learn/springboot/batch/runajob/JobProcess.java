package com.hzw.learn.springboot.batch.runajob;

import org.springframework.batch.item.ItemProcessor;
import org.springframework.stereotype.Component;

@Component
public class JobProcess implements ItemProcessor<String, String>{

	@Override
	public String process(String item) throws Exception {
		System.out.println("proc: " + item);
		return "P"+item;
	}


}
