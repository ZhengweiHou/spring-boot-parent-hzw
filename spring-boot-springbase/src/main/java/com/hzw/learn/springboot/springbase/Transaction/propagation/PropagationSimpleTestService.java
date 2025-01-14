package com.hzw.learn.springboot.springbase.Transaction.propagation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 * @ClassName PropagationSimpleTestService
 * @Description TODO
 * @Author houzw
 * @Date 2022/5/30
 **/
@Component
public class PropagationSimpleTestService extends AbstractPropagationTestService {

    @Autowired
    PropagationSimpleTestService propagationSimpleTestService;

    @Transactional(propagation = Propagation.REQUIRED)
    public void case1(){
        showTransaction();
        _insert1(1,"1");
//        try {this.propagationSimpleTestService.tx1();}catch (Exception e){};
//        try {this.propagationSimpleTestService.tx2();;}catch (Exception e){};
//        try {this.propagationSimpleTestService.tx3();;}catch (Exception e){};
        this.propagationSimpleTestService.tx1();
        this.propagationSimpleTestService.tx2();
        this.propagationSimpleTestService.tx3();

        _insert1(7,"123");
    }

    @Transactional(propagation = Propagation.NOT_SUPPORTED)
    public void tx1(){
        showTransaction();
        _insert1(2,"2");
        _insert1(3,"123"); // 失败
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void tx2(){
        showTransaction();
        _insert1(4,"4");
        _insert1(5,"123"); // 失败
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void tx3(){
        showTransaction();
        _insert1(6,"4");
    }

}
