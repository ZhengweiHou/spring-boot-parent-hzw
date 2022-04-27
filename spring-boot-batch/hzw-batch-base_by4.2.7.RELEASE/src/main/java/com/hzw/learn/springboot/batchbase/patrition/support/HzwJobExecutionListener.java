package com.hzw.learn.springboot.batchbase.patrition.support;

import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobExecutionListener;
import org.springframework.stereotype.Component;

/**
 * @ClassName HzwJobExecutionListener
 * @Description TODO
 * @Author houzw
 * @Date 2022/4/27
 **/
@Component
public class HzwJobExecutionListener implements JobExecutionListener {
    @Override
    public void beforeJob(JobExecution jobExecution) {
        System.out.println("JobExecutionListener");
    }

    @Override
    public void afterJob(JobExecution jobExecution) {

    }
}
