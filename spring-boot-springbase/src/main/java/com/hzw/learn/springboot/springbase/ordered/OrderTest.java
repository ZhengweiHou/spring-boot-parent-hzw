package com.hzw.learn.springboot.springbase.ordered;

import org.junit.Test;
import org.springframework.core.annotation.AnnotationAwareOrderComparator;

import java.util.ArrayList;
import java.util.Iterator;

/**
 * @ClassName OrderTest
 * @Description TODO
 * @Author houzw
 * @Date 2024/7/26
 **/
public class OrderTest {

    @Test
    public void orderTest1(){
        ArrayList<Object> al = new ArrayList<Object>();
        al.add(new Omax());
        al.add(new Omin());
        al.add(new Onone()); // 未重写getOrder
        al.add(new O3());
        al.add(new O1());
        al.add(new O2());
        al.add("11111");

        Iterator<Object> iter1 = al.iterator();
        while (iter1.hasNext()){
            Object o = iter1.next();
            System.out.println(o.getClass());
        }

        AnnotationAwareOrderComparator.sort(al);
        iter1 = al.iterator();
        while (iter1.hasNext()){
            Object o = iter1.next();
            System.out.println(o.getClass());
        }
    }
}
