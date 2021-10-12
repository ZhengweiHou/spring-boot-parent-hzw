package com.hzw.learn.springboot.batchbase.patrition.hstep;

import com.google.gson.Gson;
import org.springframework.batch.item.ItemWriter;

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
