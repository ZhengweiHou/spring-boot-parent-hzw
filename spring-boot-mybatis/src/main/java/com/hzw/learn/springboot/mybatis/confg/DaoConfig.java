package com.hzw.learn.springboot.mybatis.confg;

import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.BeanFactoryUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;
import org.springframework.core.annotation.Order;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;

import com.hzw.learn.springboot.mybatis.plugins.AuditLogInterceptorNew;

@Configuration
@ImportResource(locations = { "classpath:spring-mybatis.xml" })
public class DaoConfig {
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

	@Autowired // 这样也能注入拦截器
	List<Interceptor> interceptors;

	@Autowired
	AuditLogInterceptorNew auditLogInterceptorNew;

	@Bean("sqlSessionFactory")
	@ConditionalOnClass({ DataSource.class })
	public SqlSessionFactory sqlSessionFactory(DataSource dataSource, ApplicationContext applicationContext)
			throws Exception {
		System.out.println(interceptors.size());
		System.out.println(auditLogInterceptorNew.getClass());

		String configLocationSource = "classpath:mybatis-config.xml";
		String mapperLocations = "classpath*:mybatis-mapping/**/*.xml";

		SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();

		PathMatchingResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();

		/* ===1. 指定DateSource=== */
		sqlSessionFactoryBean.setDataSource(dataSource);

		/* ===2. 指定configLocation配置文件=== */
//		ResourcePatternResolver resolver1 = (ResourcePatternResolver) new PathMatchingResourcePatternResolver();
		sqlSessionFactoryBean.setConfigLocation(resolver.getResource(configLocationSource));

		/* ===3. 指定实体类映射文件=== */
//		resolver.getResources(mapperLocations); // throws IOException
//		ResourcePatternResolver resolver2 = (ResourcePatternResolver) new PathMatchingResourcePatternResolver();
		Resource[] mapperResources = resolver.getResources(mapperLocations);
		sqlSessionFactoryBean.setMapperLocations(mapperResources);

		/* ===4. Interceptor的动态注入=== */
		Map<String, Interceptor> interceptors = BeanFactoryUtils.beansOfTypeIncludingAncestors(applicationContext,
				Interceptor.class, false, false);
//		if(interceptors.size()>0){
		Interceptor[] interceptorsArr = new Interceptor[interceptors.size()];
		sqlSessionFactoryBean.setPlugins(interceptors.values().toArray(interceptorsArr));
//		}

		return sqlSessionFactoryBean.getObject();
	}

}
