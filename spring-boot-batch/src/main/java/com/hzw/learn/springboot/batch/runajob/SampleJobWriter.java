package com.hzw.learn.springboot.batch.runajob;

import java.util.List;

import org.springframework.batch.item.ItemWriter;
import org.springframework.stereotype.Component;
@Component
public class SampleJobWriter implements ItemWriter<String>{

	@Override
	public void write(List<? extends String> proceItems) throws Exception {
		System.out.println("writ: "+ proceItems);
	}

}
