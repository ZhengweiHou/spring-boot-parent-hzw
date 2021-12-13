package com.hzw.learn.springboot.springbase.OrderOfMethod;

import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.Scanner;

@FixMethodOrder(MethodSorters.JVM)

public class OrderOfMethodTest {

    @Test
    public void test1() throws InterruptedException {
        ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext("OrderOfMethod/OrderOfMethod.xml");

        Scanner scanner = new Scanner(System.in);
    }

}
