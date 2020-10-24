package com.hzw.learn.springboot.batchbase.patritionDemo.hstep;

import com.google.gson.Gson;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.StepExecution;
import org.springframework.batch.core.scope.context.StepSynchronizationManager;
import org.springframework.batch.item.*;

public class HReader implements ItemReader {

    private static final Logger log = LoggerFactory.getLogger(HReader.class);

    int index = 0;
    final int readSixe = 9;
    static int aaa = 0;

    @Override
    public Object read() throws Exception, UnexpectedInputException, ParseException, NonTransientResourceException {
        StepExecution stepExecution = StepSynchronizationManager.getContext().getStepExecution();
        ExecutionContext executionContext = stepExecution.getExecutionContext();
        log.debug("executionContext:{}", new Gson().toJson(executionContext));
        Integer item = null;
        aaa++;
        if (index < readSixe)
            item =  index++;

//        log.debug("Header read item={}，readhash={}，aaa={}", item, this.hashCode(), aaa);
        // System.out.println(Thread.currentThread().getId() + ":HReader read item=" + item + "readhash" + this.hashCode() + "   " + aaa);
        return item==null? null:item.toString();
    }

}



