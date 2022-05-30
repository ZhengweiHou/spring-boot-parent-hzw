package com.hzw.learn.springboot.batchbase.patrition.support.listener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.*;
import org.springframework.batch.item.ExecutionContext;
import org.springframework.stereotype.Component;


@Component
public class HzwJobAndStepExecutionListener implements JobExecutionListener, StepExecutionListener {
    private Logger log  = LoggerFactory.getLogger(getClass());
    public static ThreadLocal<String> stepName = new ThreadLocal<>();

    @Override
    public void beforeStep(StepExecution stepExecution) {
        ExecutionContext executionContext = stepExecution.getExecutionContext();
        System.out.println("=beforeStep StepExecutionListener name:" + stepExecution.getStepName() + " skip:"  + stepExecution.getSkipCount());
        stepName.set(stepExecution.getStepName());
    }

    @Override
    public ExitStatus afterStep(StepExecution stepExecution) {
        System.out.println("=afterStep");
        return stepExecution.getExitStatus();
    }

    @Override
    public void beforeJob(JobExecution jobExecution) {

        System.out.println("=beforeJob");
    }

    @Override
    public void afterJob(JobExecution jobExecution) {
        System.out.println("=afterJob");
    }
}
