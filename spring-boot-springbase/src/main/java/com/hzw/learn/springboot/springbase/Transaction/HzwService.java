package com.hzw.learn.springboot.springbase.Transaction;

import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;

public class HzwService {

    JdbcTemplate jdbcTemplate;

    @Transactional
    public void insertHZ(){
        Object[] o = {"hzw",18,"haha",new Date()};
        doInsertH(o);
        doIsertZ(o);
    }

    @Transactional
    public void insertHZ_Exception2(){
        Object[] o1 = {"hzw",18,"haha",new Date()};
        doInsertH(o1);
        Object[] o2 = {"hzw",18,null,new Date()}; // notnull
        doIsertZ(o2);
    }

    @Transactional
    public void insertHZ_Exception2_but_try(){
        Object[] o1 = {"hzw",18,"haha",new Date()};
        doInsertH(o1);
        try {
            Object[] o2 = {"hzw",18,null,new Date()}; // notnull
            doIsertZ(o2);
        }catch (Exception e){
            throw new RuntimeException("hello,I'm db erro!");
//            System.out.println("123123");
        }

    }

    @Transactional
    public void insertH(){
        Object[] o = {"hzw",18,"haha",new Date()};
        doInsertH(o);
    }
    @Transactional
    public void insertZ(){
        Object[] o = {"hzw",18,"haha",new Date()};
        doIsertZ(o);
    }


    private void doInsertH(Object[] o){
        String sqlH="insert H (name,age,notnull,date) values(?,?,?,?)";
        int ih=jdbcTemplate.update(sqlH, o);
        System.out.println("ih:" + new Gson().toJson(ih));
    }
    private void doIsertZ(Object[] o){
        String sqlZ="insert Z (name,age,notnull,date) values(?,?,?,?)";
        int iz=jdbcTemplate.update(sqlZ, o);
        System.out.println("iz:" + new Gson().toJson(iz));
    }

    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }
}
