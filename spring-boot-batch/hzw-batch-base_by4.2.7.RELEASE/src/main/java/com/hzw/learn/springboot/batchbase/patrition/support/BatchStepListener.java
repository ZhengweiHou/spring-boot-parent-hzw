package com.hzw.learn.springboot.batchbase.patrition.support;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.ExitStatus;
import org.springframework.batch.core.StepExecution;
import org.springframework.batch.core.StepExecutionListener;
import org.springframework.batch.item.ExecutionContext;


public class BatchStepListener implements StepExecutionListener {
    private Logger log  = LoggerFactory.getLogger(getClass());

    @Override
    public void beforeStep(StepExecution stepExecution) {
        ExecutionContext executionContext = stepExecution.getExecutionContext();
    }

    @Override
    public ExitStatus afterStep(StepExecution stepExecution) {
        return stepExecution.getExitStatus();
    }
}
