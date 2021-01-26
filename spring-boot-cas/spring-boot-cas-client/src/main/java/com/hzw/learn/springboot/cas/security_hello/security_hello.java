package com.hzw.learn.springboot.cas.security_hello;

import org.springframework.boot.SpringApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.XmlWebApplicationContext;

import java.io.IOException;

/**
 * Hello world!
 *
 */
public class security_hello
{
    public static void main( String[] args ) throws IOException {
        SpringApplication.run(SecurityHelloConfig.class, args);
    }
}
