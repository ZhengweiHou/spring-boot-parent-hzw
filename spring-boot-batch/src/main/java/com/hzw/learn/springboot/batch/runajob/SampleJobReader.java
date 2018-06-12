package com.hzw.learn.springboot.batch.runajob;

import java.util.ArrayList;

import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.NonTransientResourceException;
import org.springframework.batch.item.ParseException;
import org.springframework.batch.item.UnexpectedInputException;
import org.springframework.stereotype.Component;
@Component
public class SampleJobReader implements ItemReader<String>{
	
	int index = 0;
	ArrayList<String> list = new ArrayList<String>();
	{
		list.add("1");	list.add("2");	list.add("3");
		list.add("4");	list.add("5");	list.add("6");
		list.add("7");	list.add("8");	list.add("9");
		list.add("10");	list.add("11");	list.add("12");
		list.add("13");	list.add("14");	list.add("15");
		list.add("16");	list.add("17");	list.add("18");
	}
	
	
	@Override
	public String read() throws Exception, UnexpectedInputException,ParseException, NonTransientResourceException 
	{
		String item;
		if(index >= list.size()){
			item =  null;
		}else{
			item =  list.get(index++);
		}
		
		System.out.println("read: "+item);
		
		return item;
		
	}


}
