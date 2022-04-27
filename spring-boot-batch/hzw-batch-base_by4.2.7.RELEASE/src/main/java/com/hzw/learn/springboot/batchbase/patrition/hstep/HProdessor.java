package com.hzw.learn.springboot.batchbase.patrition.hstep;

import com.hzw.learn.springboot.batchbase.patrition.service.IBatchTestService;
import com.hzw.learn.springboot.batchbase.patrition.support.Contents;
import com.hzw.learn.springboot.batchbase.patrition.support.HzwStepExecutionListener;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.transaction.support.TransactionSynchronizationManager;

public class HProdessor implements ItemProcessor {
    @Autowired
    IBatchTestService batchTestService1;

    @Autowired
    JdbcTemplate jdbcTemplate;

    @Override
    public Object process(Object item) throws Exception {

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
