package com.hzw.learn.springboot.mybatis.confg;

import java.util.Map;

import javax.sql.DataSource;

import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.BeanFactoryUtils;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;

@Configuration
@ImportResource(locations={"classpath:spring-mybatis.xml"})
public class DaoConfig{
	
//	@Bean
//	@ConditionalOnBean(AuditLogInterceptor.class)
//	public SqlSessionFactoryBean auditLogInterceptorConfg(SqlSessionFactoryBean sqlSessionFactory, AuditLogInterceptor auditLogInterceptor){
//		System.out.println("=====================");
//		Interceptor[] plugins = {auditLogInterceptor};
//		sqlSessionFactory.setPlugins(plugins);
//		sqlSessionFactory.getObject()
//		return sqlSessionFactory;
//	}
	
	
	
//	<!-- SqlSessionFactory -->
//	<bean id="sqlSessionFactory" class="org.mybatis.spring.SqlSessionFactoryBean">
//		<property name="dataSource" ref="dataSource" />
//		<!-- mybatis配置文件路径 -->
//		<property name="configLocation" value="classpath:mybatis-config.xml" />
//		
//		<property name="plugins">  
//			<array>
//				<ref bean="auditLogInterceptor"/>
//			</array>
//		</property>
//		
//		<!-- 实体类映射文件路径 -->
//		<property name="mapperLocations" value="classpath*:mybatis-mapping/**/*.xml" />
//	</bean>
	@Bean
	@ConditionalOnClass({ DataSource.class})
//	@ConditionalOnBean(DataSource.class)
	public SqlSessionFactoryBean sqlSessionFactoryBean(DataSource dataSource, ApplicationContext applicationContext) throws Exception{
		
		String configLocationSource = "classpath:mybatis-config.xml";
		String mapperLocations = "classpath*:mybatis-mapping/**/*.xml";
		
		SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
		
		
		/*===1. 指定DateSource===*/
		sqlSessionFactoryBean.setDataSource(dataSource);
		
		/*===2. 指定configLocation配置文件===*/
		ResourcePatternResolver resolver1 = (ResourcePatternResolver) new PathMatchingResourcePatternResolver();
		sqlSessionFactoryBean.setConfigLocation(resolver1.getResource(configLocationSource));
		
		/*===3. 指定实体类映射文件===*/
//		resolver.getResources(mapperLocations); // throws IOException
		ResourcePatternResolver resolver2 = (ResourcePatternResolver) new PathMatchingResourcePatternResolver();
		Resource[] mapperResources = resolver2.getResources(mapperLocations);
		sqlSessionFactoryBean.setMapperLocations(mapperResources);
		
		/*===4. 指定实体类映射文件===*/
		Map<String, Interceptor> interceptors = BeanFactoryUtils.beansOfTypeIncludingAncestors(applicationContext, Interceptor.class, false, false);
//		if(interceptors.size()>0){
			Interceptor[] interceptorsArr = new Interceptor[interceptors.size()];
			sqlSessionFactoryBean.setPlugins(interceptors.values().toArray(interceptorsArr));
//		}
		sqlSessionFactoryBean.afterPropertiesSet();
		
		return sqlSessionFactoryBean;
	}
	
	
//	<!-- 配置sqlsession 产生这个实例就是通过 sqlsessionTemplate来实现的 -->
//	<bean id="sqlSession" class="org.mybatis.spring.SqlSessionTemplate">
//		<constructor-arg index="0">
//			<ref bean="sqlSessionFactory" />
//		</constructor-arg>
//	</bean>
	@Bean
	public SqlSessionTemplate sqlSessionTemplate(SqlSessionFactoryBean sqlSessionFactoryBean) throws Exception{
		return new SqlSessionTemplate(sqlSessionFactoryBean.getObject());
	}
	
}
