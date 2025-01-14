package com.hzw.learn.springboot.springbase.Transaction.propagation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.support.TransactionSynchronizationManager;

import java.util.List;
import java.util.Map;

/**
 * @ClassName PropagationTest1Service
 * @Description TODO
 * @Author houzw
 * @Date 2022/4/25
 **/

@Component
public class PropagationTest1Service extends AbstractPropagationTestService implements IPropagationTestService{
    @Autowired
    IPropagationTestService propagationTest2Service;

    @Transactional
    public int tx1(){
        showTransaction();
        _insert1(1,"1");
        return _query1(1);
    }

    @Transactional
    public int tx2() {
        showTransaction();
        return propagationTest2Service.tx2();
    }

    @Transactional
    public int tx3() {
        showTransaction();
        return propagationTest2Service.tx3();
    }

}
