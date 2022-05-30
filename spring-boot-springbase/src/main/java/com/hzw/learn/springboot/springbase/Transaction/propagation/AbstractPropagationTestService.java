package com.hzw.learn.springboot.springbase.Transaction.propagation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.transaction.support.TransactionSynchronizationManager;

import java.util.List;
import java.util.Map;

/**
 * @ClassName AbstractPropagationTestService
 * @Description TODO
 * @Author houzw
 * @Date 2022/4/25
 **/
public class AbstractPropagationTestService {
    @Autowired
    JdbcTemplate jdbcTemplate;

    /* CRUD Base */
    public void _insert1(int id, String name) {
        System.out.println("INSERT INTO test (id, name) VALUES (" + id + ", '" + name + "');");
        jdbcTemplate.update("INSERT INTO test (id, name) VALUES (" + id + ", '" + name + "');");
    }

    public void _update1(int id, String name) {
        jdbcTemplate.update("update test set name='"+name+"' where id = " + id);
    }

    public List<Map<String, Object>> _list1() {
        List<Map<String, Object>> list = jdbcTemplate.queryForList("select * from test");
        return list;
    }

    public int _query1(int id) {
        List<Map<String, Object>> list = jdbcTemplate.queryForList("select * from test where id = " + id);

        return list.size();
    }

    public void _delete1(int id) {
        jdbcTemplate.execute("delete from test where id='" + id + "'");
    }


    public void showTransaction(){
        System.out.println("txname:" + TransactionSynchronizationManager.getCurrentTransactionName() + " isolationLv:" + TransactionSynchronizationManager.getCurrentTransactionIsolationLevel());
    }


    public JdbcTemplate getJdbcTemplate() {
        return jdbcTemplate;
    }

    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }
}
