//package com.hzw.learn.springboot.mybatis.plugins;
//
//import org.apache.ibatis.mapping.MappedStatement;
//import org.apache.ibatis.mapping.SqlCommandType;
//import org.apache.ibatis.plugin.Invocation;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
//import org.springframework.stereotype.Component;
//
//import com.hzw.learn.springboot.mybatis.plugins.audit.AuditLogInteface;
//
//
//@Component("auditLog")
//@ConditionalOnBean(AuditEntitys.class)
//public class AuditLogHander implements AuditLogInteface {
//
//	private static final Logger logger = LoggerFactory.getLogger(AuditLogHander.class);
//	
//	@Autowired
//	private AuditEntitys auditEntitys;
//	
//	@Override
//	public Object intercept(Invocation invocation) throws Throwable {
//		MappedStatement mappedStatement = (MappedStatement) invocation.getArgs()[0];
//		SqlCommandType commandType = mappedStatement.getSqlCommandType();
//		logger.info("AuditLogInterceptor:操作类型【{}】............",commandType);
//		
//		logger.info("审计列表为：【{}】", auditEntitys.getAuditEntitys());
//		
//		return invocation.proceed();
//	}
//
//
//}
