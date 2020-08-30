package com.hzw.learn.springboot.batchbase.hello;

import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import org.springframework.batch.core.*;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.repository.JobExecutionAlreadyRunningException;
import org.springframework.batch.core.repository.JobInstanceAlreadyCompleteException;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.repository.JobRestartException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

@FixMethodOrder(MethodSorters.JVM)
public class TestMain_API {
    ApplicationContext applicationContext = null;
    @Before
    public void init(){
        applicationContext = new AnnotationConfigApplicationContext(HelloConfig.class);
    }

    @Test
    public void test() throws JobParametersInvalidException, JobExecutionAlreadyRunningException,
				 JobRestartException, JobInstanceAlreadyCompleteException {
        JobLauncher jobLauncher = (JobLauncher) applicationContext.getBean("jobLauncher");
        Job job = (Job) applicationContext.getBean("helloJob");
        JobRepository jobRepository = (JobRepository) applicationContext.getBean("jobRepository");


        JobParametersBuilder jobParametersBuilder = new JobParametersBuilder();
        JobExecution jobExecution = jobLauncher.run(job,jobParametersBuilder.toJobParameters());

        ExitStatus es = jobExecution.getExitStatus();
        if (es.getExitCode().equals(ExitStatus.COMPLETED.getExitCode())) {
            System.out.println("任务正常完成");
        } else {
            System.out.println("任务失败，exitCode=" + es.getExitCode());
        }

    }

}


