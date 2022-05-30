package com.hzw.learn.springboot.batchbase.support.listener;

import org.springframework.batch.core.ExitStatus;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobExecutionListener;
import org.springframework.batch.core.StepExecution;
import org.springframework.batch.core.listener.StepExecutionListenerSupport;

/**
 * @ClassName HzwJobExecutionListener
 * @Description TODO
 * @Author houzw
 * @Date 2022/4/27
 **/
//@Component
public class HzwStepExecutionListenerBySupport extends StepExecutionListenerSupport {
    public HzwStepExecutionListenerBySupport(){}

    public void beforeStep(StepExecution stepExecution) {
        System.out.println("HzwStepExecutionListenerBySupport beforeStep");
    }

    public ExitStatus afterStep(StepExecution stepExecution) {
        System.out.println("HzwStepExecutionListenerBySupport afterStep");
        return null;
    }
}
