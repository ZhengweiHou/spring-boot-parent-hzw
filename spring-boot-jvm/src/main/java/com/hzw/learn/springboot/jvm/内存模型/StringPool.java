package com.hzw.learn.springboot.jvm.内存模型;

import java.lang.reflect.Field;

public class StringPool {
    public static void main(String[] args) throws NoSuchFieldException, IllegalAccessException, InterruptedException {
        String hello = "hello1";
        System.out.println("hello1" + "hello1".hashCode());

        Field value =  String.class.getDeclaredField("value");
        value.setAccessible(true);
        value.set(hello,"hello2222".toCharArray());

        System.out.println("hello1" + "hello1".hashCode());

//        Thread.sleep(500);
//
//        new Thread(new Runnable() {
//            @Override
//            public void run() {
//                try {
//                    Thread.sleep(500);
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }
//                System.out.println("hello1" + "hello1".hashCode());
//                String hello2 = "hello2222";
//                System.out.println("hello2222" + "hello2222".hashCode());
//            }
//        }).start();
//
//        String hello2 = "hello2222";
//        System.out.println("hello2222" + "hello2222".hashCode());
//
//        String hello3 = "hello3333";
//        System.out.println("hello3333" + "hello3333".hashCode());
//
//        Thread.sleep(1000 * 1000);
    }
}

