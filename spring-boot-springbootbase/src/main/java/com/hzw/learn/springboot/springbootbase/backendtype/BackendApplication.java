package com.hzw.learn.springboot.springbootbase.backendtype;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ImportResource;
import org.springframework.context.annotation.PropertySource;

@SpringBootApplication
@ComponentScan(basePackages = { "com.hzw.learn.springboot.springbootbase.backendtype" })
@ImportResource("classpath:backendtype/backendtype.xml")
public class BackendApplication {

	public static void main(String[] args) {
		args = new String[]{"1","2","3"};
		new SpringApplicationBuilder(BackendApplication.class)
				.properties(
						"spring.config.location=classpath:backendtype/application_backend.yml"
						,"spring.main.web-application-type=none"
						)
				.run(args);
//		SpringApplication.run(BackendApplication.class, args);
		System.out.println("==");
	}
}
