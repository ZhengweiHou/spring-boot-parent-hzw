package com.hzw.learn.springboot.batch.runajob;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersInvalidException;
import org.springframework.batch.core.configuration.JobRegistry;
import org.springframework.batch.core.launch.JobInstanceAlreadyExistsException;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.launch.JobOperator;
import org.springframework.batch.core.launch.NoSuchJobException;
import org.springframework.batch.core.repository.JobExecutionAlreadyRunningException;
import org.springframework.batch.core.repository.JobInstanceAlreadyCompleteException;
import org.springframework.batch.core.repository.JobRestartException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping(value = "/sampleJob")
public class SampleJobLaunchController {
	private Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	JobOperator jobOperator;

	@Autowired
	JobLauncher jobLauncher;

	@Autowired
	JobRegistry jobRegistry;

	@Autowired
	Job sampleJob;

	@RequestMapping("start")
	@ResponseBody
	public String startJob() {
		long executionId;
		try {
			executionId = jobOperator.start("sampleJob", String.valueOf(Math.random()));
			logger.info("批量Job启动成功,执行Job的id为{}", executionId);
			return String.valueOf(executionId);
		} catch (NoSuchJobException | JobInstanceAlreadyExistsException | JobParametersInvalidException e) {
			e.printStackTrace();
			return e.getStackTrace().toString();
		}
	}

	@RequestMapping("launch")
	@ResponseBody
	public String launchJob() {
		try {
			System.out.println(jobRegistry.getJobNames());

			JobExecution jobExecution = jobLauncher.run(sampleJob, new JobParameters());
			logger.info(jobExecution.toString());
			return jobExecution.getExitStatus().getExitCode();
		} catch (JobExecutionAlreadyRunningException | JobRestartException | JobInstanceAlreadyCompleteException
				| JobParametersInvalidException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return e.getMessage();
		}
	}

}
