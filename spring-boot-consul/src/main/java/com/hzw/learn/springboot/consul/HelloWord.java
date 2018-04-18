package com.hzw.learn.springboot.consul;

import java.text.ParseException;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("")
public class HelloWord{
	
	@Autowired
	private EnvValue envValue;
	
	@ResponseBody
	@RequestMapping("/*")
	public String index() throws ParseException {
		
		Object enva = System.getenv("env-a");
		Map<String, String> envMaps = envValue.getEnvmaps();
		System.out.println("get value from systemenv whith 'env-a':" + enva);
		System.out.println("envMaps:" + envMaps);
		
		return envMaps.toString();
	}
	
	
}