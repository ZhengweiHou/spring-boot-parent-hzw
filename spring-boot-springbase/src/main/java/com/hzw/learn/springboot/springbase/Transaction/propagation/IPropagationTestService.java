package com.hzw.learn.springboot.springbase.Transaction.propagation;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.transaction.support.TransactionSynchronizationManager;

import java.util.List;
import java.util.Map;

/**
 * @ClassName IPropagationTest1Service
 * @Description TODO
 * @Author houzw
 * @Date 2022/4/25
 **/
public interface IPropagationTestService {

    public int tx1();
    public int tx2();
    public int tx3();




}
