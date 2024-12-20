package com.hzw.learn.springboot.javabase.apache_pool;

import org.apache.commons.pool.impl.GenericObjectPool;

import java.util.Date;

/**
 * @ClassName GenericObjectPoolTest
 * @Description TODO
 * @Author houzw
 * @Date 2024/7/2
 **/
public class GenericObjectPoolTest {
    public static void main(String[] args) throws Exception {
        GenericObjectPool<Date> gop = new GenericObjectPool<Date>();
        gop.addObject();

        Date o1 = gop.borrowObject();
        System.out.println(o1);
    }
}
