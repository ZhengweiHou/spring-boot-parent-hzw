package com.hzw.learn.springboot.batchbase.hello;

import com.alibaba.druid.pool.DruidDataSource;
import org.junit.Test;
import org.springframework.batch.core.*;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.repository.JobExecutionAlreadyRunningException;
import org.springframework.batch.core.repository.JobInstanceAlreadyCompleteException;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.repository.JobRestartException;
import org.springframework.batch.test.JobLauncherTestUtils;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.jdbc.core.simple.SimpleJdbcCallOperations;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.jdbc.core.simple.SimpleJdbcOperations;

import java.util.Date;

public class TestMain_XML {
    @Test
    public void test_hello() throws Exception {
        ApplicationContext ctx = new ClassPathXmlApplicationContext("hello/hello.xml");

        JobLauncher jobLauncher = (JobLauncher) ctx.getBean("jobLauncher");
        Job job = (Job) ctx.getBean("helloJob");
        JobRepository jobRepository = (JobRepository) ctx.getBean("jobRepository");

//        JobLauncherTestUtils jobLauncherTestUtils = new JobLauncherTestUtils();
//        jobLauncherTestUtils.setJobRepository(jobRepository);
//        jobLauncherTestUtils.setJob(job);
//        jobLauncherTestUtils.setJobLauncher(jobLauncher);
//        JobExecution jobExecution = jobLauncherTestUtils.launchJob();

        JobParametersBuilder jobParametersBuilder = new JobParametersBuilder();
        jobParametersBuilder.addDate("data",new Date());
        JobExecution jobExecution = jobLauncher.run(job,jobParametersBuilder.toJobParameters());

        ExitStatus es = jobExecution.getExitStatus();
        if (es.getExitCode().equals(ExitStatus.COMPLETED.getExitCode())) {
            System.out.println("任务正常完成");
        } else {
            System.out.println("任务失败，exitCode=" + es.getExitCode());
        }
    }

    @Test
    public void test_hello_mysql() throws JobParametersInvalidException, JobExecutionAlreadyRunningException, JobRestartException, JobInstanceAlreadyCompleteException {
        ApplicationContext ctx = new ClassPathXmlApplicationContext("hello/hellowithmysql.xml");

        JobLauncher jobLauncher = (JobLauncher) ctx.getBean("jobLauncher");
        Job job = (Job) ctx.getBean("helloJob");
        JobRepository jobRepository = (JobRepository) ctx.getBean("jobRepository");

        JobParametersBuilder jobParametersBuilder = new JobParametersBuilder();
        jobParametersBuilder.addDate("data",new Date());
        jobParametersBuilder.addString("tempStr","h");
        JobExecution jobExecution = jobLauncher.run(job,jobParametersBuilder.toJobParameters());
    }
}


