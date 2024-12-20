package com.hzw.learn.springboot.batchbase.patrition.hstep;

import com.hzw.learn.springboot.batchbase.patrition.support.listener.HzwStepExecutionListener;
import com.hzw.learn.springboot.batchbase.scoperrtest.HzwBean2;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import com.hzw.learn.springboot.batchbase.patrition.service.IBatchTestService;
import com.hzw.learn.springboot.batchbase.patrition.support.Contents;

import java.util.HashMap;
import java.util.Map;

public class HProdessor implements ItemProcessor {

    @Autowired
    IBatchTestService batchTestService1;

    @Autowired
    JdbcTemplate jdbcTemplate;

    @Autowired
    HzwBean2 hzw3;

    @Autowired
    Map hzwmapping;

    @Override
    public Object process(Object item) throws Exception {

        System.out.println("=====hzwbean:" + hzwmapping.size());
        String temp = (String) item;

        System.out.println(HzwStepExecutionListener.stepName.get() + " [processor] "
                + " get " + temp
                + " return " + item
        );

        // 第一次数据库操作（spring-batch创建）
        jdbcTemplate.update("insert into test (branch, type, item) value (?,?,?)", Contents.BRANCH,"HProdessor.process",item);
        // 第二次数据库操作 REQUIRES_NEW
        batchTestService1.tx1((String)item);
        // 第三次数据库操作 REQUIRED
        batchTestService1.tx2((String)item);
        // 第四次数据库操作 NESTED
        batchTestService1.tx3((String)item);

        return item;
    }
}
