package com.hzw.learn.springboot.drools.demo1;

import java.util.HashMap;
import java.util.Map;

import org.drools.KnowledgeBase;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.hzw.learn.springboot.drools.Message;

@Component
public class Route1_Test {
	
	@Autowired
	private BeanFactory beanFactory;
	
//	@Autowired
	KnowledgeBase route1;
	
	public void runRoute1() {
//		Map<String, String> keys = new HashMap<String, String>();
		Message keys = new Message();
		keys.put("key1", "1");
		keys.put("key2", "2");
		
//		route1 = (KnowledgeBase) beanFactory.getBean("route1");
//		route1.newStatelessKnowledgeSession().execute(keys);
	}
}
