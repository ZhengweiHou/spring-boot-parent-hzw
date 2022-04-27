package com.hzw.learn.springboot.batchbase.patrition.support;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.ExitStatus;
import org.springframework.batch.core.StepExecution;
import org.springframework.batch.core.StepExecutionListener;
import org.springframework.batch.item.ExecutionContext;
import org.springframework.stereotype.Component;


@Component
public class HzwStepExecutionListener implements StepExecutionListener {
    private Logger log  = LoggerFactory.getLogger(getClass());
    public static ThreadLocal<String> stepName = new ThreadLocal<>();

    @Override
    public void beforeStep(StepExecution stepExecution) {
        ExecutionContext executionContext = stepExecution.getExecutionContext();
        System.out.println("StepExecutionListener name:" + stepExecution.getStepName() + " skip:"  + stepExecution.getSkipCount());
        stepName.set(stepExecution.getStepName());
    }

    @Override
    public ExitStatus afterStep(StepExecution stepExecution) {
        return stepExecution.getExitStatus();
    }
}
