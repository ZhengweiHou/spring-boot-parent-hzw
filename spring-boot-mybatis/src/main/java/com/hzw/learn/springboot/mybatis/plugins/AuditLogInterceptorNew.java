package com.hzw.learn.springboot.mybatis.plugins;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.ibatis.executor.Executor;
import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.mapping.ParameterMapping;
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
import org.springframework.stereotype.Component;

import com.hzw.learn.springboot.mybatis.dao.StudentDao;
import com.hzw.learn.springboot.mybatis.model.Student;
@Component
@ConditionalOnBean(name="auditEntitys")
@Intercepts({
	@Signature(type = Executor.class, method = "update", args = {MappedStatement.class, Object.class })
})
public class AuditLogInterceptorNew implements Interceptor {

	private static final Logger logger = LoggerFactory.getLogger(AuditLogInterceptorNew.class);
	
	private String tableName;
	
	private String entityName;
	
	@Autowired
	private HashMap<String, Map<String, String>> auditEntitys;
	
	@Override
	public Object intercept(Invocation invocation) throws Throwable {
		Object[] args = invocation.getArgs();
		Object obj = null;
		if(args.length>1){
			obj = invocation.getArgs()[1];
		}
		
		MappedStatement mappedStatement = (MappedStatement) invocation.getArgs()[0];
		
		List<ParameterMapping> parameterMappings = mappedStatement.getBoundSql(obj).getParameterMappings();
		
		SqlCommandType commandType = mappedStatement.getSqlCommandType();
		logger.info("记录审计日志,操作类型[{}],参数类型:[{}],目标sql定义位置[{}],目标sql:[\n{}\n]", commandType, null==obj?"null":obj.getClass().getName(), mappedStatement.getSqlSource(), mappedStatement.getBoundSql(obj).getSql());

		switch (commandType) {
		//	UNKNOWN, INSERT, UPDATE, DELETE, SELECT, FLUSH;
		case INSERT:
			this.onPostInsert(mappedStatement, obj);
			break;
		case UPDATE:
			
			break;
		case DELETE:
	
			break;
		default:
			break;
		}
		
		return invocation.proceed();
	}
	
	@Override
	public Object plugin(Object target) {
		if (target instanceof Executor) {
			// 调用@Intercepts注解的处理方法，获取注解中子注解@Signature参数对应的对象（这里指定的是类型是Executor）
			return Plugin.wrap(target, this);
		} else {
			return target;
		}
	}

	@Override
	public void setProperties(Properties properties) {
		logger.info("properties【{}】",properties);
	}

	/**
	 * INSERT 操作拦截处理
	 * @param mappedStatement
	 * @param obj
	 * @throws SecurityException 
	 * @throws NoSuchFieldException 
	 * @throws IllegalAccessException 
	 * @throws IllegalArgumentException 
	 */
	private void onPostInsert(MappedStatement mappedStatement, Object obj) throws NoSuchFieldException, SecurityException, IllegalArgumentException, IllegalAccessException{
		// 保存 插入日志
				if(null == obj){
					return;
				}
				
				// 获取要记录日志的字段
				List<ParameterMapping> parameterMappings = mappedStatement.getBoundSql(obj).getParameterMappings();
				if(parameterMappings.size()<1){
					return;
				}
				
//				Date optDate = new Date();
//				// 获取业务日期
//				Date businessDate = globalManagementService.getSystemStatus(OrganizationContextHolder.getCurrentOrg()).getBusinessDate();
					
				for (ParameterMapping parameterMapping : parameterMappings) {
					String fieldName = parameterMapping.getProperty();
					Field tempField = obj.getClass().getDeclaredField(fieldName);
					tempField.setAccessible(true);
					
					Object value = tempField.get(obj);
					System.out.println(fieldName + ":" + value);
					
//						parameterMapping.g
//						Field tempField = obj.getClass().getDeclaredField("123");
				}
		
	}
	
	private void showBoundSql(BoundSql boundSql){
		List<ParameterMapping> a = boundSql.getParameterMappings();
		Object b = boundSql.getParameterObject();
		String c = boundSql.getSql();
		
		System.out.println("111");
		
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
		
	}
	
}
