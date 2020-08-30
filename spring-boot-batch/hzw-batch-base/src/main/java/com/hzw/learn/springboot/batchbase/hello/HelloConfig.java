package com.hzw.learn.springboot.batchbase.hello;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.job.flow.FlowStep;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.launch.support.SimpleJobLauncher;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.repository.support.MapJobRepositoryFactoryBean;
import org.springframework.batch.support.transaction.ResourcelessTransactionManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.PlatformTransactionManager;

@Configuration
public class HelloConfig {

    @Bean
    public PlatformTransactionManager transactionManager(){
        return new ResourcelessTransactionManager();
    }

    @Bean
    public JobRepository jobRepository() throws Exception {
//        return new MapJobRepositoryFactoryBean().getObject();
        return new MapJobRepositoryFactoryBean().getJobRepository();

    }

    @Bean
    public JobLauncher jobLauncher(JobRepository jobRepository){
        SimpleJobLauncher jobLauncher = new SimpleJobLauncher();
        jobLauncher.setJobRepository(jobRepository);
        return jobLauncher;
    }

    @Bean
    public HReader hReader(){
        return new HReader();
    }
    @Bean
    public HProdessor hProdessor(){
        return new HProdessor();
    }
    @Bean
    public HWriter hWriter(){
        return new HWriter();
    }

    @Bean
    public Step hStep() throws Exception {
	// TODO 2.1.9.RELEASE版本不支持这种配置，有没有其他配置方式？？
        return new StepBuilderFactory(jobRepository(),transactionManager())
                .get("step")
                .transactionManager(transactionManager())
                .chunk(10)
                .reader(hReader())
                .processor(hProdessor())
                .writer(hWriter())
                .build();
    }

    @Bean
    public Job helloJob() throws Exception {
	// TODO 2.1.9.RELEASE版本不支持这种配置，有没有其他配置方式？？
        return new JobBuilderFactory(jobRepository())
                .get("helloJob")
                .start(hStep())
                .build();
    }
}
