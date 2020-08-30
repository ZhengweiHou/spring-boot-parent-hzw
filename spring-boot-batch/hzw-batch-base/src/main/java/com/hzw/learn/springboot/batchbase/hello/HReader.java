package com.hzw.learn.springboot.batchbase.hello;

import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.NonTransientResourceException;
import org.springframework.batch.item.ParseException;
import org.springframework.batch.item.UnexpectedInputException;

public class HReader implements ItemReader {

    int index = 0;
    final int readSixe = 1000;

    @Override
    public Object read() throws Exception, UnexpectedInputException, ParseException, NonTransientResourceException {
        Integer item = null;
        if (index < readSixe)
            item =  index++;

        System.out.println(Thread.currentThread().getId() + ":HReader read item=" + item);
        return item;
    }

}



