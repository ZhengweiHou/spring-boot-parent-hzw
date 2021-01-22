package com.hzw.learn.springboot.springbase.Pattern.singleton.register;

import java.util.concurrent.CountDownLatch;

public class Test_register {
    public static void main(String[] args) {
        int count = 1000;
        CountDownLatch latch = new CountDownLatch(count);
        RegisterBean register = new RegisterBean();
        for (;count>0;count--){
            new Thread(() -> {
                try {
                    latch.await();
                } catch (InterruptedException e) { e.printStackTrace(); }

                //Object obj = register.getBean("java.lang.String");
//                Object obj = register.getBean("com.hzw.learn.springboot.springbase.Test.PenBean");
                Object obj = register.getBean(com.hzw.learn.springboot.springbase.Transaction.H.class.getName());

                System.out.println("" + System.currentTimeMillis() + obj);
            }).start();
            latch.countDown();
        }

    }
}
