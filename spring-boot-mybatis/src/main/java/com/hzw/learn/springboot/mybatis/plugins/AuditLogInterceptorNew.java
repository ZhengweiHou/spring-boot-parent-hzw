package com.hzw.learn.springboot.mybatis.plugins;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;
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
import org.apache.ibatis.session.ResultHandler;
import org.apache.ibatis.session.RowBounds;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.stereotype.Component;

import com.alibaba.druid.support.json.JSONUtils;
@Component
@ConditionalOnBean(name="auditEntitys")
@Intercepts(@Signature(type = Executor.class, method = "update", args = {MappedStatement.class, Object.class }))
public class AuditLogInterceptorNew implements Interceptor {

	private static final Logger logger = LoggerFactory.getLogger(AuditLogInterceptorNew.class);
	
//	@Autowired
//	private AuditEntitys auditEntitys;	
	@Autowired
	private HashMap<String, Map<String, String>> auditEntitys;
	
	@Override
	public Object intercept(Invocation invocation) throws Throwable {
		this.showSomeThing(invocation);
		
		logger.info("审计列表为：【{}】", auditEntitys);
		
		return invocation.proceed();
	}

	private void showSomeThing(Invocation invocation)throws IllegalAccessException, InvocationTargetException {
		System.out.println("==================================showTime===========================================");
		MappedStatement mappedStatement = (MappedStatement) invocation.getArgs()[0];
		Object obj = invocation.getArgs()[1];
		
//		this.reflectObj(mappedStatement);
		
//		SqlCommandType commandType = mappedStatement.getSqlCommandType();
//		logger.info("AuditLogInterceptorNew:操作类型【{}】............",commandType);
		
		System.out.println("--------------------------------------MappedStatement|||||||||||--------------------------------------------");
		BoundSql boundSql = mappedStatement.getBoundSql(obj);
		boundSql.getParameterMappings();
		this.reflectObj(boundSql);
		
		System.out.println(boundSql.getSql());
		System.out.println(mappedStatement.getSqlSource().getBoundSql(obj).getSql());
		
		System.out.println("--------------------------------------MappedStatement|||||||||||--------------------------------------------");
		
//		this.reflectObj(obj);
		
		System.out.println("==================================showEnd===========================================");
	}
	
	private void reflectObj(Object obj) throws IllegalAccessException,InvocationTargetException {
		if(null == obj)
			return;
		Class<? extends Object> clazz = obj.getClass();
		System.out.println("--------------------------------------"+clazz.getSimpleName()+"--------------------------------------------");
		
		Method[] methods = clazz.getMethods();
		for (Method method : methods) {
			
			String methodName = method.getName();
			if(methodName.startsWith("get") == false){
				continue;
			}
			System.out.print(clazz.getSimpleName() + "." + method.getName() + "=");
			String methodTemp = null;
			try {
				methodTemp = method.invoke(obj).toString();
			} catch (Exception e) {
			}
			System.out.println(methodTemp);
		}
		
/*		Field[] fields = clazz.getFields();
		
		for (Field field : fields) {
			String fieldTemp = field.getName() + "=" + field.get(obj);
			System.out.println(fieldTemp);
		}*/
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
