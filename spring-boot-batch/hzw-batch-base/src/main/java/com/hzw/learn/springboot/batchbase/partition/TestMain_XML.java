package com.hzw.learn.springboot.batchbase.partition;

import org.junit.Test;
import org.springframework.batch.core.*;
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
    public void test_hello() throws Exception {
        ApplicationContext ctx = new ClassPathXmlApplicationContext("partiton/partition.xml");

    }

}


