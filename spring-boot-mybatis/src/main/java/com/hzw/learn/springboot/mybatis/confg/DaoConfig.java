package com.hzw.learn.springboot.mybatis.confg;

import org.apache.ibatis.plugin.Interceptor;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;

import com.hzw.learn.springboot.mybatis.plugins.AuditLogInterceptor;

@Configuration
@ImportResource(locations={"classpath:spring-mybatis.xml"})
public class DaoConfig{
	
//	@Bean
//	@ConditionalOnBean(AuditLogInterceptor.class)
//	public SqlSessionFactoryBean auditLogInterceptorConfg(SqlSessionFactoryBean sqlSessionFactory, AuditLogInterceptor auditLogInterceptor){
//		System.out.println("=====================");
//		Interceptor[] plugins = {auditLogInterceptor};
//		sqlSessionFactory.setPlugins(plugins);
//		return sqlSessionFactory;
//	}
}
