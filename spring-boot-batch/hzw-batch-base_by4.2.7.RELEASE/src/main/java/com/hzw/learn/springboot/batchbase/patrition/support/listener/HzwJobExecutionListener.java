package com.hzw.learn.springboot.batchbase.patrition.support.listener;

import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobExecutionListener;

/**
 * @ClassName HzwJobExecutionListener
 * @Description TODO
 * @Author houzw
 * @Date 2022/4/27
 **/
//@Component
public class HzwJobExecutionListener implements JobExecutionListener {
    @Override
    public void beforeJob(JobExecution jobExecution) {
        System.out.println("JobExecutionListener");
    }

    @Override
    public void afterJob(JobExecution jobExecution) {

    }
}
