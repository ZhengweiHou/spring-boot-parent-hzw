package com.hzw.learn.springboot.batchbase.patrition.hstep;

import com.google.gson.Gson;
import com.hzw.learn.springboot.batchbase.patrition.support.listener.HzwStepExecutionListener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.StepExecution;
import org.springframework.batch.core.scope.context.StepSynchronizationManager;
import org.springframework.batch.item.ExecutionContext;
import org.springframework.batch.item.ItemStreamException;
import org.springframework.batch.item.support.AbstractItemCountingItemStreamItemReader;
import org.springframework.beans.factory.BeanNameAware;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

/**
 * 将文件拆分成一个个的小文件
 */
public class HzwPartitionResourceItemReader extends AbstractItemCountingItemStreamItemReader implements BeanNameAware {
    Logger log = LoggerFactory.getLogger(getClass());
    BufferedReader reader = null;

    int index = 0;


    @Override
    protected Object doRead() throws Exception {
        String item = reader.readLine();
        System.out.println(HzwStepExecutionListener.stepName.get() +" [read] "+item);
        return item;
    }

    @Override
    protected void doOpen() throws Exception {
        StepExecution stepExecution = StepSynchronizationManager.getContext().getStepExecution();
        ExecutionContext executionContext = stepExecution.getExecutionContext();
        log.debug("executionContext:{}", new Gson().toJson(executionContext));

        reader = Files.newBufferedReader(Paths.get((String) executionContext.get("partFilePath")));
    }

    @Override
    protected void doClose() throws Exception {
//        IOUtils.closeQuietly(reader);
        try {
            if (reader != null) {
                reader.close();
            }
        } catch (IOException ioe) {
            // ignore
        }
    }

    @Override
    public void setBeanName(String name) {
        log.debug("setBeanName：{}",name);
        super.setName(name);        // 调用AbstractItemCountingItemStreamItemReader.setName() 将其ExecutionContextUserSupport属性的name值赋值
    }

    public void update(ExecutionContext executionContext) throws ItemStreamException {
        super.update(executionContext);
        System.out.println("update==================");
    }
}
