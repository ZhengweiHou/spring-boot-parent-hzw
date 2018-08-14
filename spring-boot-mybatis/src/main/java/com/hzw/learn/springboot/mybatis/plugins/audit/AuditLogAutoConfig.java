//package com.hzw.learn.springboot.mybatis.plugins.audit;
//
//import java.util.Map;
//
//import org.springframework.beans.factory.BeanFactoryUtils;
//import org.springframework.context.ApplicationContext;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//
//@Configuration
//public class AuditLogAutoConfig {
//	
//	@Bean
//	public AuditLogInterceptor auditLogInterceptor(ApplicationContext ac) {
//		AuditLogInterceptor auditLogInterceptor = new AuditLogInterceptor();
//		
//		AuditLogInteface auditLog = null;
//		
//		Map<String, AuditLogInteface> auditLogs = BeanFactoryUtils.beansOfTypeIncludingAncestors(ac, AuditLogInteface.class, false, false);
//		if(auditLogs.size() > 0){
//			auditLog = (AuditLogInteface) auditLogs.values().toArray()[0];
//		}
//		
////		AuditLogInteface auditLog = BeanFactoryUtils.beanOfTypeIncludingAncestors(ac, AuditLogInteface.class, false, false);
//		if(auditLog != null){
//			auditLogInterceptor.setAuditLog(auditLog);
//		}
//		return auditLogInterceptor;
//	}
//	
//}
