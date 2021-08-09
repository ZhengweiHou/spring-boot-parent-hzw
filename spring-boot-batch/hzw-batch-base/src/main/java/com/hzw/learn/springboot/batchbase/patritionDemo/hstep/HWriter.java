package com.hzw.learn.springboot.batchbase.patritionDemo.hstep;

import com.google.gson.Gson;
import org.apache.logging.log4j.message.MessageFormatMessage;
import org.springframework.batch.item.ItemWriter;

import java.text.MessageFormat;
import java.util.List;

public class HWriter implements ItemWriter {
    @Override
    public void write(List list) throws Exception {
        System.out.println("[write] "
                + Thread.currentThread().getId()
                + ":" + this.getClass().getSimpleName()
                + " write =>" + new Gson().toJson(list)
        );
    }
}
