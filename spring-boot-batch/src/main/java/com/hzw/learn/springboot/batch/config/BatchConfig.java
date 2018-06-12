package com.hzw.learn.springboot.batch.config;

import org.springframework.batch.core.configuration.JobRegistry;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.support.JobRegistryBeanPostProcessor;
import org.springframework.batch.core.configuration.support.MapJobRegistry;
import org.springframework.batch.core.explore.JobExplorer;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.launch.support.SimpleJobLauncher;
import org.springframework.batch.core.launch.support.SimpleJobOperator;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.repository.support.MapJobRepositoryFactoryBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
@EnableBatchProcessing
public class BatchConfig {
	
	/**
	 * 
	 * @param jobRepository
	 * @return
	 */
	@Bean
	public JobLauncher jobLauncher(JobRepository jobRepository){
		SimpleJobLauncher jobLauncher = new SimpleJobLauncher();
		jobLauncher.setJobRepository(jobRepository);
		return jobLauncher;
	}
	
	/**
	 * MapJobExplorer - using in-memory DAO implementations
	 */
//	@Bean
//	public JobExplorer jobExplorer() throws Exception{
//		MapJobExplorerFactoryBean mapJobExplorerFactory = new MapJobExplorerFactoryBean();
//		mapJobExplorerFactory.setRepositoryFactory(new MapJobRepositoryFactoryBean());
//		return mapJobExplorerFactory.getObject();
//	}
	
	/**
	 * MapJobRepository - 基于内存存储的JobRepository
	 */
	@Bean
	public JobRepository jobRepository() throws Exception{
		return new MapJobRepositoryFactoryBean().getObject();
	}
	
	
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
	 * 注入job注册表，Springboot没有自动注入，如果不手动注入，jobOperator将无法使用
	 * @return
	 */
	@Bean
	public JobRegistryBeanPostProcessor jobRegistryBeanPostProcessor(){
		JobRegistryBeanPostProcessor processor = new JobRegistryBeanPostProcessor();
		processor.setJobRegistry(jobRegistry());
		return processor;
	}
	
	@Bean
	public SimpleJobOperator jobOperator(
			JobLauncher   jobLauncher,
			JobExplorer   jobExplorer,
			JobRepository jobRepository,
			JobRegistry   jobRegistry
		){
		
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
		
		return jobOperator;
	}

}
