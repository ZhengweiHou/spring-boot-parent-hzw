package com.hzw.learn.springboot.batchbase.patrition.service;

import com.hzw.learn.springboot.batchbase.patrition.support.Contents;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.support.TransactionSynchronizationManager;

/**
 * @ClassName BatchTestService1
 * @Description TODO
 * @Author houzw
 * @Date 2022/4/27
 **/
@Component
public class BatchTestService1 implements IBatchTestService{

    @Autowired
    JdbcTemplate jdbcTemplate;

    @Override
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public int tx1(String item) {
        jdbcTemplate.update("insert into test (branch, type, item) value (?,?,?)", Contents.BRANCH,"BatchTestService1.tx1",item);
        return 0;
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public int tx2(String item) {
        jdbcTemplate.update("insert into test (branch, type, item) value (?,?,?)", Contents.BRANCH,"BatchTestService1.tx2",item);
        return 0;
    }

    @Override
    @Transactional(propagation = Propagation.NESTED)
    public int tx3(String item) {
        jdbcTemplate.update("insert into test (branch, type, item) value (?,?,?)", Contents.BRANCH,"BatchTestService1.tx3",item);
        if(item.equals("000000008")) {
            int a = 1 / 0;
        }
        return 0;
    }
}
