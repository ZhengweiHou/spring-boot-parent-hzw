package com.hzw.learn.springboot.mybatis.plugins;

import java.util.Properties;

import org.apache.ibatis.executor.Executor;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.mapping.SqlCommandType;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.plugin.Intercepts;
import org.apache.ibatis.plugin.Invocation;
import org.apache.ibatis.plugin.Plugin;
import org.apache.ibatis.plugin.Signature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;
@Component
@ConditionalOnBean(AuditEntitys.class) // 依赖AuditEntitys，若容器中没有实例AuditEntitys则本类也不实例
@Intercepts(@Signature(type = Executor.class, method = "update", args = {MappedStatement.class, Object.class}))
public class AuditLogInterceptor implements Interceptor {

	private static final Logger logger = LoggerFactory.getLogger(AuditLogInterceptor.class);
	
//	private Map<String, Map<String, String>> auditEntitys;
	@Autowired
	private AuditEntitys auditEntitys;
	
	@Override
	public Object intercept(Invocation invocation) throws Throwable {
		MappedStatement mappedStatement = (MappedStatement) invocation.getArgs()[0];
		SqlCommandType commandType = mappedStatement.getSqlCommandType();
		logger.info("AuditLogInterceptor:操作类型【{}】............",commandType);
		
		logger.info("审计列表为：【{}】", auditEntitys.getAuditEntitys());
		
		return invocation.proceed();
	}

	@Override
	public Object plugin(Object target) {
		if (target instanceof Executor) {
			return Plugin.wrap(target, this);
		} else {
			return target;
		}
	}

	@Override
	public void setProperties(Properties properties) {
		logger.info("properties【{}】",properties);
	}


}
