package com.hzw.learn.springboot.batchbase.patritionDemo.support;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.StepExecution;
import org.springframework.batch.core.partition.PartitionHandler;
import org.springframework.batch.core.partition.StepExecutionSplitter;

import java.util.Collection;

public class HzwPartitionHandler implements PartitionHandler {
    private Logger log = LoggerFactory.getLogger(getClass());

    @Override
    public Collection<StepExecution> handle(StepExecutionSplitter stepExecutionSplitter, StepExecution stepExecution) throws Exception {
        // FIXME 这里的StepExecutionSplitter 是怎么来的
        log.info("Job{execId:{},name:{}},  Step:{execId:{},name:{}}"
                , stepExecution.getJobExecutionId(), stepExecution.getJobExecution().getJobInstance().getJobName(), stepExecution.getId(), stepExecution.getStepName());
        return null;
    }
}
