package com.hzw.learn.springboot.batchbase.patrition.hstep;

import com.google.gson.Gson;
import com.hzw.learn.springboot.batchbase.patrition.support.HzwStepExecutionListener;
import org.springframework.batch.item.ItemWriter;
import org.springframework.transaction.support.TransactionSynchronizationManager;

import java.util.List;

public class HWriter implements ItemWriter {
    @Override
    public void write(List list) throws Exception {
        System.out.println(
                HzwStepExecutionListener.stepName.get() + "[write] write =>" + new Gson().toJson(list)
        );

    }
}
