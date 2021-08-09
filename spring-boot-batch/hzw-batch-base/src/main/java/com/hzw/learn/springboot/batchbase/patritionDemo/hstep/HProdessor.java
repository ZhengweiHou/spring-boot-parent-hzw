package com.hzw.learn.springboot.batchbase.patritionDemo.hstep;

import org.springframework.batch.item.ItemProcessor;

public class HProdessor implements ItemProcessor {
    @Override
    public Object process(Object item) throws Exception {

        String temp = (String) item;

//        if (temp.equals("000000004"))
//            throw new RuntimeException("我是一个错误哦！");

        System.out.println("[processor] "
                + Thread.currentThread().getId()
                + ":" + this.getClass().getSimpleName()
                + " get " + temp
                + " return " + item
        );

        return item;
    }
}
