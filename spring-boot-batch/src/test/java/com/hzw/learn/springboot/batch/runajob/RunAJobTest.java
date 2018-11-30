package com.hzw.learn.springboot.batch.runajob;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.batch.core.JobParametersInvalidException;
import org.springframework.batch.core.configuration.JobRegistry;
import org.springframework.batch.core.launch.JobInstanceAlreadyExistsException;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.launch.JobOperator;
import org.springframework.batch.core.launch.NoSuchJobException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.hzw.learn.springboot.batch.Application;

@RunWith(SpringJUnit4ClassRunner.class)

//@ContextConfiguration(classes={BatchConfig.class,JobConf.class})
//
//@ComponentScan(basePackages={"com.hzw.learn.springboot"}) 
@SpringBootTest(classes = Application.class)
public class RunAJobTest {

	@Autowired
	JobOperator jobOperator;

	@Autowired
	JobLauncher jobLauncher;

	@Autowired
	JobRegistry jobRegistry;

	@Test
	public void test() {

		System.out.println(jobRegistry.getJobNames());

		try {
			jobOperator.start("sampleJob", String.valueOf(Math.random()));
		} catch (NoSuchJobException | JobInstanceAlreadyExistsException | JobParametersInvalidException e) {
			e.printStackTrace();
		}
	}

}
