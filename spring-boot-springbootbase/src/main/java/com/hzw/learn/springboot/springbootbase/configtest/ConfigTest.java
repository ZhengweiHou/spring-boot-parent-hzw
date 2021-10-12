package com.hzw.learn.springboot.springbootbase.configtest;

import java.util.Map;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
// 指定程序启动入口
@SpringBootTest(classes= {ConfigTestApplication.class})
public class ConfigTest {
	
	@Autowired 
	public HzwEnv hzwenv;
	
	@Test 
	public void test() {
		Map<String, Object> maps = hzwenv.getHzwmaps();
		System.out.println("=================================");
		maps.forEach((k,v) -> {
			System.out.println(k + ":" + v);
		});
		System.out.println("=================================");
		
	}


}
