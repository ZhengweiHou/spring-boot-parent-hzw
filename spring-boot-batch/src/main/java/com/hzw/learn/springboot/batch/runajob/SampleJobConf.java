package com.hzw.learn.springboot.batch.runajob;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.PlatformTransactionManager;

@Configuration
public class SampleJobConf {
	@Autowired
	JobBuilderFactory jobBuilderFactory;

	@Autowired
	StepBuilderFactory stepBuilderFactory;

	@Bean
	public Job sampleJob(JobRepository jobRepository, Step sampleStep) {
		return this.jobBuilderFactory.get("sampleJob").repository(jobRepository).start(sampleStep).build();
	}

	@Bean
	public Step sampleStep(PlatformTransactionManager transactionManager, SampleJobReader read, SampleJobProcess proc,
			SampleJobWriter writer) {
		return this.stepBuilderFactory.get("sampleStep").transactionManager(transactionManager).<String, String>chunk(5)
				.reader(read).processor(proc).writer(writer).build();
	}

}
