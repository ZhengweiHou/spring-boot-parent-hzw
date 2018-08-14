//package com.hzw.learn.springboot.mybatis.plugins.audit;
//
//import java.util.Properties;
//
//import org.apache.ibatis.executor.Executor;
//import org.apache.ibatis.mapping.MappedStatement;
//import org.apache.ibatis.plugin.Interceptor;
//import org.apache.ibatis.plugin.Intercepts;
//import org.apache.ibatis.plugin.Invocation;
//import org.apache.ibatis.plugin.Plugin;
//import org.apache.ibatis.plugin.Signature;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//@Intercepts(@Signature(type = Executor.class, method = "update", args = {MappedStatement.class, Object.class}))
//public class AuditLogInterceptor implements Interceptor {
//
//	private static final Logger logger = LoggerFactory.getLogger(AuditLogInterceptor.class);
//	
//	private AuditLogInteface auditLog;
//	
//	@Override
//	public Object intercept(Invocation invocation) throws Throwable {
//		System.out.println("===================AuditLogInterceptor=========================");
//		if(auditLog != null){
//			return auditLog.intercept(invocation);
//		}else{
//			return invocation.proceed();
//		}
//	}
//
//	@Override
//	public Object plugin(Object target) {
//		if (target instanceof Executor) {
//			return Plugin.wrap(target, this);
//		} else {
//			return target;
//		}
//	}
//
//	@Override
//	public void setProperties(Properties properties) {
//		logger.info("properties【{}】",properties);
//	}
//
//	public void setAuditLog(AuditLogInteface auditLog) {
//		this.auditLog = auditLog;
//	}
//
//
//}
