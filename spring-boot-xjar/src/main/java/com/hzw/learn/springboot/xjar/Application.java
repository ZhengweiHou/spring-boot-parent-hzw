package com.hzw.learn.springboot.xjar;

import com.alibaba.cola.dto.Response;
import com.google.gson.Gson;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

import java.util.Arrays;

//@SpringBootApplication
//@ComponentScan(basePackages = { "com.hzw.learn.springboot.xjar"})
public class Application {

	public static void main(String[] args) {
//		SpringApplication.run(Application.class, args);

		System.out.println("==");

		Response rep = new Response();

		System.out.println(rep.toString());



	}
}
