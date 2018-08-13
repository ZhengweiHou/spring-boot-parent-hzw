package com.hzw.learn.springboot.mybatis.confg;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;

@Configuration
@ImportResource(locations={"classpath:spring-mybatis.xml"})
public class DaoConfig{

}
