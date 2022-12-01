package com.hzw.learn.springboot.javabase.concurrentHashMap;

import org.junit.Test;

import java.util.Date;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @ClassName ConcurrentHashMapTest
 * @Description TODO
 * @Author houzw
 * @Date 2022/11/30
 **/

public class ConcurrentHashMapTest {

    @Test
    public void test() {
        ConcurrentHashMap<String,String> map = new ConcurrentHashMap<>();
        map.putIfAbsent("test",newString());
        map.putIfAbsent("test",newString());
    }

    public static String newString(){
        System.out.println("hzw");
        return new Date().toString();
    }


}
