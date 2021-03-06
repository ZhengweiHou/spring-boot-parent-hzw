package com.hzw.learn.springboot.batchbase.hello;

import org.springframework.batch.item.ItemProcessor;
import org.springframework.beans.BeanUtils;

public class HProdessor implements ItemProcessor {
    @Override
    public Object process(Object item) throws Exception {

        Integer temp = (Integer) item;
        item = -temp;

//        if (temp > 500)
//            throw new RuntimeException("我是一个错误哦！");

        System.out.println(Thread.currentThread().getId()
                + ":" + this.getClass().getSimpleName()
                + " get " + temp
                + " return " + item
        );

        return item;
    }
}
