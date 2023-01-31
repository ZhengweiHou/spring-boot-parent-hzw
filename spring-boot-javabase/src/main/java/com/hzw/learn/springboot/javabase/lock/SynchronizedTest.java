package com.hzw.learn.springboot.javabase.lock;

import org.junit.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @ClassName SynchronizedTest
 * @Description TODO
 * @Author houzw
 * @Date 2022/12/7
 **/
public class SynchronizedTest {

    public Map<String,Object> objMap = new HashMap<>();
    {
        objMap.put("1",new Object());
        objMap.put("2",new Object());
        objMap.put("3",new Object());
    }

    public void doSynLocal() {
        Object obj = objMap.get("1");
        synchronized (obj){
            System.out.println(Thread.currentThread().getName() + " " + "sync start");
            try {
                Thread.sleep(3000);
            } catch (Exception e) {}
            System.out.println(Thread.currentThread().getName() + " " + "sync end");
        }
    }

    public void doSyn() {
        synchronized (objMap.get("1")){
            System.out.println(Thread.currentThread().getName() + " " + "sync start");
            try {
                Thread.sleep(3000);
            } catch (Exception e) {}
            System.out.println(Thread.currentThread().getName() + " " + "sync end");
        }
    }

    public static void main(String[] args) throws InterruptedException {
        SynchronizedTest syncTest = new SynchronizedTest();

        new Thread(() ->{
            syncTest.doSyn();
        },"1").start();

        new Thread(() ->{
            syncTest.doSyn();
        },"2").start();

        new Thread(() ->{
            syncTest.doSynLocal();
        },"3").start();

        new Thread(() ->{
            syncTest.doSynLocal();
        },"4").start();

        Thread.sleep(1000);
    }


}
