package com.hzw.learn.springboot.springbase.Transaction.isolation;

import com.google.gson.Gson;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.text.SimpleDateFormat;
import java.util.Date;
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
    Logger log = LoggerFactory.getLogger("hh");

    @Autowired
    IsolationSimpleTestService isolationSimpleTestService;

    CountDownLatch cd1 = new CountDownLatch(1);
    CountDownLatch cd2 = new CountDownLatch(1);

    public void case1() throws Exception {

        for (int i=0; i<1; i++) {
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
        Thread.sleep(1000);

    }


    @Transactional
    public void _updateForAddAndSelect(int id) throws Exception {
        showTransaction();
        show("q_S");
        List<Map<String, Object>> list1 = jdbcTemplate.queryForList("select * from test where id = " + id);
        System.out.println("start:" + new Gson().toJson(list1));
        show("q_F");

        cd1.await();
        show("i_S");
        jdbcTemplate.update("update test set age=age + 1 where id = " + id);
        show("i_F");
        Thread.sleep(500);

        show("q2_S");
        List<Map<String, Object>> list2 = jdbcTemplate.queryForList("select * from test where id = " + id);
        show("q2_F");
        System.out.println("end:" + new Gson().toJson(list2));
        Thread.sleep(500);
        cd2.countDown();
    }

    public void show(String msg){
        System.out.println("" + new SimpleDateFormat("HH:mm:ss.SSS").format(new Date()) + "   " + Thread.currentThread().getName() + "  " + msg);
    }


}
