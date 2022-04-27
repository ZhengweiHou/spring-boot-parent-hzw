package com.hzw.learn.springboot.springbase.Transaction.propagation;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 * @ClassName PropagationTest2Service
 * @Description TODO
 * @Author houzw
 * @Date 2022/4/25
 **/
@Component
public class PropagationTest2Service extends AbstractPropagationTestService implements IPropagationTestService{
    @Override
    public int tx1() {
        return 0;
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public int tx2() {
        showTransaction();
        _insert1(1,"1");
//        return _query1(1);
        return 0;
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public int tx3() {
        showTransaction();
        _insert1(1,"1");
        return 0;
    }
}
