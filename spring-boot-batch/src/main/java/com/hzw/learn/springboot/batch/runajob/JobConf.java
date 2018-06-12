package com.hzw.learn.springboot.batch.runajob;

import java.util.List;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobParametersIncrementer;
import org.springframework.batch.core.JobParametersValidator;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.batch.item.ItemReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.PlatformTransactionManager;

@Configuration
public class JobConf {
	@Autowired
	JobBuilderFactory jobBuilderFactory;

	@Autowired
	StepBuilderFactory stepBuilderFactory;

	@Bean
	public Job sampleJob(JobRepository jobRepository, Step sampleStep) {
		return this.jobBuilderFactory.get("sampleJob")
				.repository(jobRepository)
				.start(sampleStep)
				.build();
	}

	@Bean
	public Step sampleStep(PlatformTransactionManager transactionManager) {
	return this.stepBuilderFactory.get("sampleStep")
//				.transactionManager(transactionManager)
				.<String, String>chunk(5)
				.reader(new JobReader())
				.processor(new JobProcess())
				.writer(new JobWriter())
				.build();
	}
	

}
