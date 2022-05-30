package com.hzw.learn.springboot.batchbase.patrition;

import org.junit.Test;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.JobParametersInvalidException;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.repository.JobExecutionAlreadyRunningException;
import org.springframework.batch.core.repository.JobInstanceAlreadyCompleteException;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.repository.JobRestartException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.Date;

public class TestMain_XML {
    @Test
    public void test_hello_mysql() throws JobParametersInvalidException, JobExecutionAlreadyRunningException, JobRestartException, JobInstanceAlreadyCompleteException, InterruptedException {
        ApplicationContext ctx = new ClassPathXmlApplicationContext("partition/partitionDemo.xml");

        JobLauncher jobLauncher = (JobLauncher) ctx.getBean("jobLauncher");
        Job job = (Job) ctx.getBean("partitionDemoJob");
        JobRepository jobRepository = (JobRepository) ctx.getBean("jobRepository");

        JobParametersBuilder jobParametersBuilder = new JobParametersBuilder();
        jobParametersBuilder.addDate("data",new Date());
        jobParametersBuilder.addLong("index",1L);
//        jobParametersBuilder.addString("tempStr","h");
        JobExecution jobExecution = jobLauncher.run(job,jobParametersBuilder.toJobParameters());

        for (int i=1; i<100; i++){
            Thread.sleep(20);
        }

    }
}



