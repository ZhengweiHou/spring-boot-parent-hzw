package com.hzw.learn.springboot.mybatis.plugins;

import java.util.Properties;

import org.apache.ibatis.executor.Executor;
import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.mapping.SqlCommandType;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.plugin.Intercepts;
import org.apache.ibatis.plugin.Invocation;
import org.apache.ibatis.plugin.Plugin;
import org.apache.ibatis.plugin.Signature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Intercepts(@Signature(type = Executor.class, method = "update", args = {MappedStatement.class, Object.class}))
public class UpdateInterceptor implements Interceptor {

	private static final Logger logger = LoggerFactory.getLogger(UpdateInterceptor.class);
	
	
	@Override
	public Object intercept(Invocation invocation) throws Throwable {
		MappedStatement mappedStatement = (MappedStatement) invocation.getArgs()[0];
		SqlCommandType commandType = mappedStatement.getSqlCommandType();
		logger.info("updateInterceptor:操作类型【{}】............",commandType);
		
		Object parameter = null;  
		if (invocation.getArgs().length > 1) {  
			parameter = invocation.getArgs()[1];  
		}
		BoundSql boundSql = mappedStatement.getBoundSql(parameter);
		String sql = boundSql.getSql();
		
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
