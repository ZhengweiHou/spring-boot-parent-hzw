package com.hzw.learn.springboot.springbase.Transaction.isolation;

import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;
import java.util.concurrent.CountDownLatch;
import java.util.stream.Stream;

/**
 * @ClassName IsolationSimpleTestService
 * @Description TODO
 * @Author houzw
 * @Date 2022/5/30
 **/
@Component
public class IsolationSimpleTestService extends AbstractIsolationTestService {

    @Autowired
    IsolationSimpleTestService isolationSimpleTestService;

    CountDownLatch cd1 = new CountDownLatch(1);
    CountDownLatch cd2 = new CountDownLatch(1);

    public void case1() throws Exception {

        for (int i=0; i<2; i++) {
            new Thread(() -> {
                try {
                    isolationSimpleTestService._updateForAddAndSelect(1);
                } catch (Exception e) {
                }
            }).start();
        }
        Thread.sleep(1000);
        cd1.countDown();
        Thread.sleep(2000);
        cd2.await();

    }


    @Transactional
    public void _updateForAddAndSelect(int id) throws Exception {

        List<Map<String, Object>> list1 = jdbcTemplate.queryForList("select * from test where id = " + id);
        System.out.println("start:" + new Gson().toJson(list1));

        jdbcTemplate.update("update test set age=age + 5 where id = " + id);
        cd1.await();

        List<Map<String, Object>> list2 = jdbcTemplate.queryForList("select * from test where id = " + id);
        System.out.println("end:" + new Gson().toJson(list2));
        cd2.await();
    }


}
