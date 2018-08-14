package com.hzw.learn.springboot.mybatis.plugins;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;
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
import org.springframework.stereotype.Component;
@Component
@ConditionalOnBean(name="auditEntitys")
@Intercepts(@Signature(type = Executor.class, method = "update", args = {MappedStatement.class, Object.class}))
public class AuditLogInterceptorNew implements Interceptor {

	private static final Logger logger = LoggerFactory.getLogger(AuditLogInterceptorNew.class);
	
//	@Autowired
//	private AuditEntitys auditEntitys;	
	@Autowired
	private HashMap<String, Map<String, String>> auditEntitys;
	
	@Override
	public Object intercept(Invocation invocation) throws Throwable {
		MappedStatement mappedStatement = (MappedStatement) invocation.getArgs()[0];
		SqlCommandType commandType = mappedStatement.getSqlCommandType();
		logger.info("AuditLogInterceptorNew:操作类型【{}】............",commandType);
		
		Object obj = invocation.getArgs()[1];
		
		Class<? extends Object> clazz = obj.getClass();
		String canonicalName = clazz.getCanonicalName();
		
		Method[] methods = clazz.getMethods();
		for (Method method : methods) {
			String methodName = method.getName();
			if(methodName.startsWith("get") == false){
				continue;
			}
			System.out.println(method.invoke(obj));
		}
		
		Field[] fields = clazz.getFields();
		
		for (Field field : fields) {
			String fieldTem = field.getName() + "=" + field.get(obj);
			System.out.println(fieldTem);
		}
		
		
		logger.info("审计列表为：【{}】", auditEntitys);
		
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
