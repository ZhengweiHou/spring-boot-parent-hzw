package com.hzw.learn.springboot.batchbase.hello;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.NonTransientResourceException;
import org.springframework.batch.item.ParseException;
import org.springframework.batch.item.UnexpectedInputException;

public class HReader implements ItemReader {

    private static final Logger log = LoggerFactory.getLogger(HReader.class);

    int index = 0;
    final int readSixe = 9;
    static int aaa = 0;

    @Override
    public Object read() throws Exception, UnexpectedInputException, ParseException, NonTransientResourceException {
        Integer item = null;
        aaa++;
        if (index < readSixe)
            item =  index++;

        log.debug("Header read item={}，readhash={}，aaa={}", item, this.hashCode(), aaa);
        // System.out.println(Thread.currentThread().getId() + ":HReader read item=" + item + "readhash" + this.hashCode() + "   " + aaa);
        return item;
    }

}



