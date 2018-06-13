package com.hzw.learn.springboot.batch.config;

import javax.sql.DataSource;

import org.springframework.batch.core.configuration.JobRegistry;
import org.springframework.batch.core.configuration.annotation.BatchConfigurer;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.configuration.support.AutomaticJobRegistrar;
import org.springframework.batch.core.configuration.support.DefaultJobLoader;
import org.springframework.batch.core.configuration.support.JobRegistryBeanPostProcessor;
import org.springframework.batch.core.configuration.support.MapJobRegistry;
import org.springframework.batch.core.explore.JobExplorer;
import org.springframework.batch.core.explore.support.JobExplorerFactoryBean;
import org.springframework.batch.core.explore.support.MapJobExplorerFactoryBean;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.launch.support.SimpleJobLauncher;
import org.springframework.batch.core.launch.support.SimpleJobOperator;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.repository.support.JobRepositoryFactoryBean;
import org.springframework.batch.core.repository.support.MapJobRepositoryFactoryBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.PlatformTransactionManager;


@Configuration
@EnableBatchProcessing
public class BatchConfig // implements BatchConfigurer	// The core interface for this configuration is the BatchConfigurer
{
	
	@Autowired
	private DataSource dataSource;							// 数据源 - yml中配置
	@Autowired
	private PlatformTransactionManager transactionManager;	// 事务管理器（注意多数据源事务管理器的选择）
	
	
	/* ===========================JobLauncher==================================*/
	/**
	 * 
	 * @param JobLauncher
	 * @return
	 */
	@Bean
	public JobLauncher jobLauncher(JobRepository jobRepository){
		SimpleJobLauncher jobLauncher = new SimpleJobLauncher();
		jobLauncher.setJobRepository(jobRepository);
		return jobLauncher;
	}
	/* ===========================JobLauncher==================================*/
	
	/* ===========================JobExplorer==================================*/
	/**
	 * MapJobExplorer - using in-memory DAO implementations(基于内存)
	 */
//	@Bean
	public JobExplorer mapJobExplorer() throws Exception{
		MapJobExplorerFactoryBean mapJobExplorerFactory = new MapJobExplorerFactoryBean();
		mapJobExplorerFactory.setRepositoryFactory(new MapJobRepositoryFactoryBean());
		mapJobExplorerFactory.afterPropertiesSet();
		return mapJobExplorerFactory.getObject();
	}
	
	/**
	 * SimpleJobExplorer - read-only version of the JobRepository(基于数据库)
	 * @param dataSource
	 */
	@Bean
	public JobExplorer jobExplorer() throws Exception{
		JobExplorerFactoryBean factoryBean = new JobExplorerFactoryBean();
		factoryBean.setDataSource(this.dataSource);
//		factoryBean.setTablePrefix("HZW_"); // 设置批量表前缀，不设置默认为BATCH_
		factoryBean.afterPropertiesSet();
		return factoryBean.getObject();
	}
	/* ===========================JobExplorer==================================*/
	
	/* ===========================JobRepository==================================*/
	/**
	 * MapJobRepository - JobRepository(基于内存)
	 */
//	@Bean
	public JobRepository mapJobRepository() throws Exception{
		return new MapJobRepositoryFactoryBean().getObject();
	}
	
	/**
	 * JobRepository - (基于数据库)
	 * @return
	 * @throws Exception
	 */
	@Bean
	public JobRepository jobRepository() throws Exception{
		JobRepositoryFactoryBean factoryBean = new JobRepositoryFactoryBean();
		factoryBean.setDataSource(this.dataSource);							// 设置数据源
//		factoryBean.setTablePrefix("HZW_");									// 设置批量表前缀（默认BATCH_）
		factoryBean.setTransactionManager(this.transactionManager);			// 设置事务管理器
//		factoryBean.setIsolationLevelForCreate("ISOLATION_REPEATABLE_READ");// 设置create* method的事务隔离级别-默认为"ISOLATION_SERIALIZABLE"
//		factoryBean.setDatabaseType("MySQL");									// 设置数据库类型 - 默认会从dataSource去识别获取
//		factoryBean.afterPropertiesSet();
		JobRepository jobRepository = factoryBean.getObject();
		return jobRepository;
	}
	
	/* ===========================JobRepository==================================*/
	
	/* ===========================JobRegistry==================================*/
	/**
	 * MapJobRegistry - 任务注册
	 * @return
	 */
	@Bean
	public JobRegistry jobRegistry(){
		MapJobRegistry jobRegistry = new MapJobRegistry();
		return jobRegistry;
	}
	/**
	 * 注入job注册表，Springboot没有自动注入，如果不手动注入，MapJobRegistry将无法使用-----配合上面的MapJobRegistry
	 * @return
	 */
	@Bean
	public JobRegistryBeanPostProcessor jobRegistryBeanPostProcessor(){
		JobRegistryBeanPostProcessor processor = new JobRegistryBeanPostProcessor();
		processor.setJobRegistry(jobRegistry());
		return processor;
	}
	
	////////////////////////AutomaticJobRegistrar///////////////////////
//	public AutomaticJobRegistrar registrar(){
//		AutomaticJobRegistrar registrar = new AutomaticJobRegistrar();
//		registrar.setJobLoader(new DefaultJobLoader());
//		registrar.setApplicationContextFactories(applicationContextFactories());
//		registrar.afterPropertiesSet();
//		return registrar;
//	}
//	public DefaultJobLoader jobLoader(){
//		
//	}
	
	
	////////////////////////AutomaticJobRegistrar///////////////////////
	
	
	/* ===========================JobRegistry==================================*/
	
	
	
//	@Bean
//	public JobBuilderFactory jobBuilderFactory(JobRepository jobRepository){
//		return new JobBuilderFactory(jobRepository);
//	}
//	
//	@Bean
//	public StepBuilderFactory stepBuilderFactory(JobRepository jobRepository, PlatformTransactionManager transactionManager){
//		return new StepBuilderFactory(jobRepository, transactionManager);
//	}
	
	
	
	@Bean
	public SimpleJobOperator jobOperator(
			JobLauncher   jobLauncher,
			JobExplorer   jobExplorer,
			JobRepository jobRepository,
			JobRegistry   jobRegistry
		) throws Exception{
		
		SimpleJobOperator jobOperator = new SimpleJobOperator();
		/*======1.JobLauncher======*/
//		SimpleJobOperator jobOperator = new SimpleJobOperator();
		
		/*======2.JobExplorer======*/
//		SimpleJobExplorer jobExplorer = new SimpleJobExplorer(jobInstanceDao, jobExecutionDao, stepExecutionDao, ecDao);
		
		/*======3.JobRepository======*/
//		SimpleJobRepository jobRepository = new SimpleJobRepository(jobInstanceDao, jobExecutionDao, stepExecutionDao, ecDao);
		
		/*======4.JobRegistry======*/
//		MapJobRegistry jobRegistry = new MapJobRegistry();
		
		jobOperator.setJobExplorer(jobExplorer);
		jobOperator.setJobRepository(jobRepository);
		jobOperator.setJobRegistry(jobRegistry);
		jobOperator.setJobLauncher(jobLauncher);
		
//		jobOperator.setJobExplorer(jobExplorer());
//		jobOperator.setJobRepository(jobRepository());
//		jobOperator.setJobRegistry(jobRegistry());
//		jobOperator.setJobLauncher(jobLauncher(jobRepository()));
		
		return jobOperator;
	}

}
